package com.example.learnspring.aspect.logic;

public class SimpleMinCalculator implements MinCalculator {
    @Override
    public double min(double a, double b) {
        var result = Math.min(a, b);

        System.out.printf("min(%f,%f) = %f%n", a, b, result);
        return result;
    }
}
