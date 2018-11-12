package top.youshang520i.util;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import top.youshang520i.quartz.MyJobFactory;

import java.io.IOException;
import java.util.Properties;
/**
 *  @Author:youshang520i
 *  @Date:$date  21:59
 *  @Description : 将Quartz注入到Spring Bean中
 */
@Configuration
public class QuartzConfigration {

    @Autowired
    private MyJobFactory myJobFactory;  //自定义的factory

    //获取工厂bean  2
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        try {
            schedulerFactoryBean.setQuartzProperties(quartzProperties());
            schedulerFactoryBean.setJobFactory(myJobFactory);//将主动权交给Quartz管理，不然的话jobExecutionContext（在制作工作的时候中的一个参数）将拿不到值
            return schedulerFactoryBean;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //指定quartz.properties  3
    /**
     *  @Author:youshang520i
     *  @Date:$date  20:51
     *  @Description :读取配置文件
     */
    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties")); //加载配置文件,如果没有配置就加载Quartz默认的配置文件
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

    //创建schedule Bean  1
    @Bean(name = "scheduler")
    public Scheduler scheduler() {
        return schedulerFactoryBean().getScheduler();
    }
}