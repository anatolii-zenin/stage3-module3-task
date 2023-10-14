package com.mjc.school.main;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@ComponentScan({"com.mjc.school.main", "com.mjc.school.controller"})
@EnableJpaAuditing
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class MainSpringConfig {
}
