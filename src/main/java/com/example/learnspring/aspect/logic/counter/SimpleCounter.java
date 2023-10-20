package com.example.learnspring.aspect.logic.counter;

import java.util.concurrent.atomic.AtomicInteger;

public class SimpleCounter implements Counter {

    private final AtomicInteger atomicInteger = new AtomicInteger();

    @Override
    public void increase() {
        atomicInteger.incrementAndGet();
    }

    @Override
    public int getCount() {
        return atomicInteger.get();
    }
}
