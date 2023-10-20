package com.example.learnspring.aspect.logic.counter;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CounterIntroduction {

    @DeclareParents(
            value = "com.example.learnspring.aspect.logic.Standard*Calculator",
            defaultImpl = SimpleCounter.class
    )
    public Counter counter;


    @After(value = "execution(* com.example.learnspring.aspect.logic.*Calculator.*(..)) " +
            "&& this(counter)", argNames = "counter")
    public void increaseCount(final Counter counter) {

        counter.increase();
    }


}
