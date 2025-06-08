package com.example.packminigames.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    // Цей метод використовується для простого мапування URL на view (без контролера)
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Мапуємо "/" (кореневий URL) на view з назвою "home"
        // Spring Boot з Thymeleaf або іншим шаблонізатором шукатиме home.html або home.jsp
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login").setViewName("login"); // Мапуємо /login на view login
        registry.addViewController("/about").setViewName("about"); // Просто приклад сторінки "Про нас"
    }

    // Тут можна додавати інші налаштування, наприклад:
     @Override
     public void addResourceHandlers(ResourceHandlerRegistry registry) {
         registry.addResourceHandler("/static/**")
                 .addResourceLocations("classpath:/static/");
     }

    // @Override
    // public void addInterceptors(InterceptorRegistry registry) {
    //     registry.addInterceptor(new MyCustomInterceptor());
    // }
}
