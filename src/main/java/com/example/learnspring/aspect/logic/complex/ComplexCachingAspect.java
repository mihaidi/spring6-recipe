package com.example.learnspring.aspect.logic.complex;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
public class ComplexCachingAspect {

    private final Map<String, Complex> cache = new ConcurrentHashMap<>();


    // call is AspectJ Specific method
    @Around(value = "call(public com.example.learnspring.aspect.logic.complex.Complex.new(int, int)) && args(a,b)", argNames = "joinPoint,a,b")
    public Object cacheAround(final ProceedingJoinPoint joinPoint, int a, int b) {

        var key = a + "," + b;
        return cache.compute(key, (key1, val) -> checkCacheOrCalculate(joinPoint, key1, val));
    }

    private Complex checkCacheOrCalculate(final ProceedingJoinPoint joinPoint,
                                          final String key,
                                          final Complex current) {

        if (current == null) {

            try {
                System.out.println("Cache MISS for %s" .formatted(key));
                return (Complex) joinPoint.proceed();
            } catch (Throwable ex) {
                throw new IllegalArgumentException(ex);
            }

        } else {
            System.out.println("Cache HIT for %s" .formatted(key));
            return current;
        }
    }

}
