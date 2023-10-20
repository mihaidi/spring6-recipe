package com.example.learnspring.comunication;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CheckoutEvent {

    private final String name;

    private final LocalDateTime localDateTime;

    public CheckoutEvent(String name, LocalDateTime localDateTime) {
        this.name = name;
        this.localDateTime = localDateTime;
    }
}
