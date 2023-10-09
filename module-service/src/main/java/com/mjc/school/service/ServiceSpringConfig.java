package com.mjc.school.service;


import org.springframework.context.annotation.*;

@Configuration
@ComponentScan({"com.mjc.school.service", "com.mjc.school.repository"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ServiceSpringConfig {
}
