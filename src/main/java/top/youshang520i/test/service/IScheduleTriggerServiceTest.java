package top.youshang520i.test.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.youshang520i.pojo.ScheduleTrigger;
import top.youshang520i.service.ScheduleTriggerServiceI;
import top.youshang520i.test.BaseTest;

import static org.junit.Assert.*;
/**
 *  @Author:youshang520i
 *  @Date:$date  15:32
 *  @Description : 测试数据
 */
public class IScheduleTriggerServiceTest extends BaseTest {

    @Autowired
    private ScheduleTriggerServiceI scheduleTriggerServiceI;

    private ScheduleTrigger scheduleTrigger;

    @Before
    public void before(){
        scheduleTrigger = new ScheduleTrigger();
    }

    @Test
    public void add() throws Exception {
        scheduleTrigger.setId(null);
        scheduleTrigger.setCron("*/5 * * * * ?");
        scheduleTrigger.setStatus("1");
        scheduleTrigger.setJobName("com.zking.q03.quartz.MyJob1");
        scheduleTrigger.setJobGroup("group-1");

        scheduleTriggerServiceI.insert(scheduleTrigger);
    }

    @Test
    public void add2() throws Exception {
        scheduleTrigger.setId(null);
        scheduleTrigger.setCron("*/10 * * * * ?");
        scheduleTrigger.setStatus("1");
        scheduleTrigger.setJobName("com.zking.q03.quartz.MyJob2");
        scheduleTrigger.setJobGroup("group-2");

        scheduleTriggerServiceI.add(scheduleTrigger);
    }

    @Test
    public void add3() throws Exception {
        scheduleTrigger.setId(null);
        scheduleTrigger.setCron("*/10 * * * * ?");
        scheduleTrigger.setStatus("1");
        scheduleTrigger.setJobName("com.zking.q03.quartz.MyJob3");
        scheduleTrigger.setJobGroup("group-3");

        //动态添加任务的参数
        scheduleTriggerServiceI.add(scheduleTrigger);
    }

}