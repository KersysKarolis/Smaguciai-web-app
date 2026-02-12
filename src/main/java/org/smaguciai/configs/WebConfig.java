package org.smaguciai.configs;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Value("${file.upload-dir}")
    private String uploadDir;
@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
registry.addResourceHandler("/uploads/**")
        .addResourceLocations("file:" + uploadDir+ "/");
}
    @PostConstruct
    public void init() {
        System.out.println("WEB CONFIG LOADED, uploadDir = " + uploadDir);
    }
}
