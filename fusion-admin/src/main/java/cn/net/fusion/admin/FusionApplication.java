package cn.net.fusion.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages = {"cn.net.fusion"})
@MapperScan(basePackages = {"cn.net.**.mapper"})
@EnableTransactionManagement  // 启用事务（自动事务使用@Transcational(注解)，手动事务使用TransactionFactory(自定义的全局事务管理器)）
public class FusionApplication {

    public static void main(String[] args) {
        SpringApplication.run(FusionApplication.class, args);
    }

}
