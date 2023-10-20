package com.example.learnspring.controller;

import com.example.learnspring.model.Dog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @GetMapping("/test")
    public String handle() {

        return "Hello";
    }


    @PostMapping("/test")
    public Dog handle(@RequestBody Dog dog) {

        return dog;
    }
}
