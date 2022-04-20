package com.sg.gate.musicserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 开启热部署：
 * 1、首先要在pom文件里添加devtools依赖 2、在下面的springboot打包插件中修改fork属性为true 3、在设置中找到高级设置 ，勾选应用程序运行时编译器自动生成允许。
 *
 */
@SpringBootApplication
@MapperScan("com.sg.gate.musicserver.dao")
public class MusicServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicServerApplication.class, args);
    }

}
