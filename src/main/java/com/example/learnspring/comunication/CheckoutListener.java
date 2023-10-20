package com.example.learnspring.comunication;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CheckoutListener {


    @EventListener
    public void onApplicationEvent(CheckoutEvent event) {

        System.out.println(event);
    }

}
