package com.example.projet;

import com.example.projet.services.AuthService;
import com.example.projet.services.CategoryService;
import com.example.projet.services.ExpenseService;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.Arrays;

@Configuration
public class MyConfig {
    @Bean
    public ResourceConfig resourceConfig(){
        ResourceConfig resourceConfig= new ResourceConfig();
        return resourceConfig.packages("com.example.projet.services");
    }




}
