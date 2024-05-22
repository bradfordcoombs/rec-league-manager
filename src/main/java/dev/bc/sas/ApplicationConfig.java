package dev.bc.sas;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackageClasses = ApplicationConfig.class)
@PropertySource("classpath:application.properties")
public class ApplicationConfig {


}
