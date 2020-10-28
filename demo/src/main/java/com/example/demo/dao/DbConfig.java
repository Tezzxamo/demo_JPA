package com.example.demo.dao;

import com.example.demo.dao.dbo.Clazz;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackageClasses = DbConfig.class)
@EnableJpaRepositories
@EntityScan(basePackageClasses = Clazz.class)
public class DbConfig {

}
