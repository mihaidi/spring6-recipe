package com.example.learnspring.model;

import com.example.learnspring.annotation.ToUpperCase;
import com.example.learnspring.annotation.ToUppperCase;
import jakarta.annotation.PostConstruct;


public class Dog {

    @ToUpperCase
    private String name;

    public Dog(String name) {
        this.name = name;
    }

    @ToUppperCase
    public void spek(final String name) {
        System.out.println(name);
    }

    @PostConstruct
    public void display() {
        System.out.println(name);
    }
}
