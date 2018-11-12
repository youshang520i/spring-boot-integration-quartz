package top.youshang520i.quartz;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;
/**
 *  @Author:youshang520i
 *  @Date:$date  21:54
 *  @Description : 让Quartz与Spring产生联系（在Spring中有一个类为Quartz提供了方法，选择重写了该方法）
 */
@Component
public class MyJobFactory extends AdaptableJobFactory {

    @Autowired
    private AutowireCapableBeanFactory capableBeanFactory;  //这个是Spring提供的类
    /**
     *  @Author:youshang520i
     *  @Date:$date  22:01
     *  @Description : 创建Job实例
     */
    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object jobInstance = super.createJobInstance(bundle);//qz框架反射机制创建jobBean
        capableBeanFactory.autowireBean(jobInstance); //这一步解决不能spring注入bean的问题
        return jobInstance;
    }
}
