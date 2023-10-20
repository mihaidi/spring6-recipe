package com.example.learnspring.aspect;

import com.example.learnspring.annotation.ToUpperCase;
import com.example.learnspring.annotation.ToUppperCase;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ToUpperCaseV2WithAspects {


    @Around(value = "@annotation(annotation)",
            argNames = "joinPoint ,annotation")
    public Object handleToUppperCase(final ProceedingJoinPoint joinPoint,
                                     final ToUppperCase annotation) throws Throwable {

        var a = ((String) joinPoint.getArgs()[0]).toUpperCase();


        System.out.println(annotation.value());

        return joinPoint.proceed(new Object[]{a});
    }

}
