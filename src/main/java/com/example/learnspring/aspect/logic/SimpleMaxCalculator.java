package com.example.learnspring.aspect.logic;

public class SimpleMaxCalculator implements MaxCalculator {
    @Override
    public double max(double a, double b) {

        var result = Math.max(a, b);

        System.out.printf("max(%f,%f) = %f%n", a, b, result);
        return result;
    }
}
