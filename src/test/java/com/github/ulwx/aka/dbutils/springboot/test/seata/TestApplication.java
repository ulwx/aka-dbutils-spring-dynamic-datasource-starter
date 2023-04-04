package com.github.ulwx.aka.dbutils.springboot.test.seata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TestApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(TestApplication.class);
        ConfigurableApplicationContext context=application.run(args);



    }


}
