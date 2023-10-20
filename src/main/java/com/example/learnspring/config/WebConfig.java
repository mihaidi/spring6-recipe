package com.example.learnspring.config;

import com.example.learnspring.model.Dog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class WebConfig {


    @Bean
    public Dog dog() {
        final Dog tuzic = new Dog("tuzic");

        return tuzic;
    }
}
