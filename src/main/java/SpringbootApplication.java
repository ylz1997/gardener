import com.github.pagehelper.PageHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Properties;

/**
 该注解指定项目为springboot，由此类当作程序入口
 自动装配 web 依赖的环境

 **/
@Configuration
@SpringBootApplication
@EnableAutoConfiguration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.jw.kids", "com.jw.kids.service", "com.jw.base"})
@MapperScan(basePackages = {"com.jw.kids.dao"})

public class SpringbootApplication {

    @Bean
    public PageHelper pageHelper(){

        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum","true");//多余的数据是否作为新的页
        properties.setProperty("rowBoundsWithCount","true");//读取总数
        properties.setProperty("reasonable","true");//
        properties.setProperty("dialect","mysql");    //配置mysql数据库的方言
        pageHelper.setProperties(properties);
        return pageHelper;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

}