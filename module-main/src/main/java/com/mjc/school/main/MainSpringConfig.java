package com.mjc.school.main;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan({"com.mjc.school.main", "com.mjc.school.controller"})
@EnableAspectJAutoProxy
public class MainSpringConfig {
}
