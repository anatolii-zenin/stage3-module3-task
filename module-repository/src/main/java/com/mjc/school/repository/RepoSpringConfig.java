package com.mjc.school.repository;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan()
@PropertySource("classpath:repository.properties")
public class RepoSpringConfig {
}
