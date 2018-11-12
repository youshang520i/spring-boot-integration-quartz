package top.youshang520i.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.core.jmx.QuartzSchedulerMBean;

import java.util.Date;

/**
 * @actuor youshang520i
 * @create 2018-11-10 22:54
 * @desc 测试Job
 */
public class TestJob implements Job{
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("**************执行成功***********"+new Date().getTime());
    }
}
