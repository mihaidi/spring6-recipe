package com.example.learnspring.aspect.logic;

import org.springframework.stereotype.Component;

@Component
public class StandardUnitCalculator implements UnitCalculator {
    @Override
    public double kilogramToPound(double kilogram) {

        var pound = kilogram * 2.2;
        System.out.printf("%f kilogram = %f pound %n", kilogram, pound);
        return pound;
    }

    @Override
    public double kilometerToMile(double kilometer) {
        var mile = kilometer * 0.62;
        System.out.printf("%f kilometer = %f mile %n", kilometer, mile);

        return mile;
    }
}
