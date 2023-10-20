package com.example.learnspring.aspect.logic;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CalculatorIntroduction {


    @DeclareParents(
            value = "com.example.learnspring.aspect.logic.StandardArithmeticCalculator", // extend this class , with MaxCalculator functionality
            defaultImpl = SimpleMaxCalculator.class
    )
    public MaxCalculator maxCalculator;


    @DeclareParents(
            value = "com.example.learnspring.aspect.logic.StandardArithmeticCalculator",
            defaultImpl = SimpleMinCalculator.class)
    public MinCalculator minCalculator;

}
