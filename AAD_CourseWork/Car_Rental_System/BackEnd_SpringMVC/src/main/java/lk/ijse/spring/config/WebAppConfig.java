package lk.ijse.spring.config;

import lk.ijse.spring.advisor.AppWideAdvisor;
import lk.ijse.spring.controller.EmployeeController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackageClasses = {EmployeeController.class, AppWideAdvisor.class})
@EnableWebMvc
public class WebAppConfig {
}
