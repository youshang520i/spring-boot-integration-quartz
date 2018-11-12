package top.youshang520i.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import top.youshang520i.dao.ScheduleTriggerMapper;
import top.youshang520i.pojo.ScheduleTrigger;
import top.youshang520i.pojo.ScheduleTriggerParam;
import top.youshang520i.service.ScheduleTriggerParamServiceI;
import top.youshang520i.service.ScheduleTriggerServiceI;
import java.util.List;
/**
 *  @Author:youshang520i
 *  @Date:$date  15:30
 *  @Description : 实现ScheduleTriggerServiceI接口
 */
@Service(value = "scheduleTriggerServiceI")
public class ScheduleTriggerServiceImpl implements ScheduleTriggerServiceI {
    private Logger logger = LogManager.getLogger(ScheduleTriggerServiceImpl.class);

    @Autowired
    private Scheduler scheduler;   //在QuartzConfigration中注册了Bean

    @Autowired
    private ScheduleTriggerMapper scheduleTriggerMapper;

    @Autowired
    private ScheduleTriggerParamServiceI scheduleTriggerParamServiceI;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return scheduleTriggerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ScheduleTrigger record) {
        return scheduleTriggerMapper.insert(record);
    }

    @Override
    public int insertSelective(ScheduleTrigger record) {
        return scheduleTriggerMapper.insertSelective(record);
    }

    @Override
    public ScheduleTrigger selectByPrimaryKey(Integer id) {
        return scheduleTriggerMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ScheduleTrigger record) {
        return scheduleTriggerMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ScheduleTrigger record) {
        return scheduleTriggerMapper.updateByPrimaryKey(record);
    }

    /**
     *  @Author:youshang520i
     *  @Date:$date  22:16
     *  @Description : 被替代
     */
    @Override
    public List<ScheduleTrigger> queryAll() {
        return scheduleTriggerMapper.queryAll();
    }

    @Override
    //@Scheduled(cron = "0 0 23:00 * * ?")  //每天晚上11点调用这个方法来更新quartz中的任务
    @Scheduled(cron = "*/3 * * * * ?")  //每隔30秒执行一次更新quartz中的任务，调小一点好测试
    public void refreshTrigger() {
        try {
            logger.info("ScheduleTriggerService.refreshTrigger");
            //查询出数据库中所有的定时任务
            List<ScheduleTrigger> jobList = scheduleTriggerMapper.queryAll();
            if (jobList != null) {
                for (ScheduleTrigger scheduleJob : jobList) {

                    String status = scheduleJob.getStatus(); //该任务触发器目前的状态
                    TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
                    CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);   //获得scheduler的key在上一次基础（git）中有讲解
                    //说明本条任务还没有添加到quartz中
                    if (null == trigger) {
                        if (status.equals("0")) { //如果是禁用，则不用创建触发器
                            continue;
                        }

                        JobDetail jobDetail = null; //定义Quartz上下文
                        try {
                            //创建JobDetail（数据库中job_name存的任务全路径，这里就可以动态的把任务注入到JobDetail中）
                            jobDetail = JobBuilder.newJob((Class<? extends Job>) Class.forName(scheduleJob.getJobName()))
                                    .withIdentity(scheduleJob.getJobName(), scheduleJob.getJobGroup()).build();

                            //TODO 可以添加一些额外的参数到任务的上下文中
                            Integer scheduleTriggerId = scheduleJob.getId();
                            List<ScheduleTriggerParam> paramList = scheduleTriggerParamServiceI.listByScheduleTriggerId(scheduleTriggerId);
                            JobDataMap jobDataMap = jobDetail.getJobDataMap();
                            for (ScheduleTriggerParam p : paramList) {
                                jobDataMap.put(p.getName(), p.getValue());
                            }

                            //表达式调度构建器
                            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob
                                    .getCron());
                            //按新的cronExpression表达式构建一个新的trigger
                            trigger = TriggerBuilder.newTrigger().withIdentity(scheduleJob.getJobName(), scheduleJob.getJobGroup()).withSchedule(scheduleBuilder).build();
                            //把trigger和jobDetail注入到调度器
                            scheduler.scheduleJob(jobDetail, trigger);
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else {  //说明查出来的这条任务，已经设置到quartz中了
                        // Trigger已存在，先判断是否需要删除，如果不需要，再判定是否时间有变化
                        if (status.equals("0")) { //如果是禁用，从quartz中删除这条任务
                            JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
                            scheduler.deleteJob(jobKey);
                            continue;
                        }
                        String searchCron = scheduleJob.getCron(); //获取数据库的
                        String currentCron = trigger.getCronExpression();
                        if (!searchCron.equals(currentCron)) {  //说明该任务有变化，需要更新quartz中的对应的记录
                            //表达式调度构建器
                            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(searchCron);

                            //按新的cronExpression表达式重新构建trigger
                            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
                                    .withSchedule(scheduleBuilder).build();

                            //按新的trigger重新设置job执行
                            scheduler.rescheduleJob(triggerKey, trigger);
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("定时任务每日刷新触发器任务异常，在ScheduleTriggerServiceImpl的方法refreshTrigger中，异常信息：", e);
            throw new RuntimeException(e);
        }
    }



    /**
     *  @Author:youshang520i
     *  @Date:$date  22:15
     *  @Description  :  先不管
      */
    @Override
    public void add(ScheduleTrigger scheduleTrigger) {
        //scheduleTriggerMapper.add(scheduleTrigger);
    }
}