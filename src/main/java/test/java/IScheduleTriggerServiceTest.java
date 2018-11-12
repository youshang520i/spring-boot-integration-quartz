package test.java;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.youshang520i.pojo.ScheduleTrigger;
import top.youshang520i.service.ScheduleTriggerServiceI;


public class IScheduleTriggerServiceTest {

    @Autowired
    private ScheduleTriggerServiceI scheduleTriggerServiceI;

    @Autowired
    private ScheduleTrigger scheduleTrigger;

    @Before
    public void before(){
        scheduleTrigger = new ScheduleTrigger();
    }

    @Test
    public void add() throws Exception {
        System.out.println("2333333");
        scheduleTrigger.setId(null);
        scheduleTrigger.setCron("*/5 * * * * ?");
        scheduleTrigger.setStatus("1");
        scheduleTrigger.setJobName("com.zking.q03.quartz.MyJob1");
        scheduleTrigger.setJobGroup("group-1");
        System.out.println(scheduleTriggerServiceI);
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