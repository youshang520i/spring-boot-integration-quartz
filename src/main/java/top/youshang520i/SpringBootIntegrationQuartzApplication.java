package top.youshang520i;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/**
 *  @Author:youshang520i
 *  @Date:$date  15:34
 *  @Description : 主入口
 */
@SpringBootApplication
@MapperScan("top.youshang520i.dao")
@EnableScheduling
@EnableTransactionManagement
public class SpringBootIntegrationQuartzApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootIntegrationQuartzApplication.class, args);
	}
}
