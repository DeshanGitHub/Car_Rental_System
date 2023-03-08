package lk.ijse.spring.config;

import lk.ijse.spring.advisor.AppWideAdvisor;
import lk.ijse.spring.controller.EmployeeController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackageClasses = {EmployeeController.class, AppWideAdvisor.class})
@EnableWebMvc
public class WebAppConfig implements WebMvcConfigurer {


    /*
     * First and foremost we need to configure MultipartResolver
     */
    @Bean
    public MultipartResolver multipartResolver() {
        System.out.println("Im from multipart Resolver");
        return new StandardServletMultipartResolver();
    }

    /*
     * You have to override this method and allocate the url and location for uploaded resources
     * */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("\\CustomerImages\\**").addResourceLocations("\\CustomerImages\\");
        registry.addResourceHandler("\\CarImages\\**").addResourceLocations("\\CarImages\\");
    }

}
