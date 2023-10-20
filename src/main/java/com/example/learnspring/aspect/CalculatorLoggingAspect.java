package com.example.learnspring.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
@ComponentScan(basePackages = "com.example.learnspring.aspect.logic")
public class CalculatorLoggingAspect {


    // If you don't want to write same advice expression multiple times
    @Pointcut("execution(* com.example.learnspring.aspect.logic.ArithmeticCalculator.add(..))")
    private void loggingOperation() {
    }


    @Pointcut(value = "execution(* com.example.learnspring.aspect.logic.ArithmeticCalculator.*(..))")
    private void anyMethodFromArithmeticCalculatorPointCut() {
    }


    // affect all methods that are annotated with @LoggingRequired ,
    // annotation must be set on class, method and not interface
    @Pointcut("@annotation(com.example.learnspring.annotation.LoggingRequired)")
    private void methodsMarkedByAnnotation() {

    }


    @Pointcut("within(com.example.learnspring.aspect.logic.*)")
    private void allMethodsFromLogicPackage() {

    }

    @Pointcut("within(com.example.learnspring.aspect.logic..*)")
    private void allMethodsFromLogicPackageAndSubpackages() {

    }

    @Pointcut("within(com.example.learnspring.aspect.logic.StandardArithmeticCalculator)")
    private void allMethodsFromParticularClass() {

    }

    @Pointcut("within(com.example.learnspring.aspect.logic.ArithmeticCalculator+)")
    private void allClassesThatImplementsInterface() {

    }

    //Combine Pointcut Expressions

    @Pointcut("within(com.example.learnspring.aspect.logic.ArithmeticCalculator+)")
    public void arithmeticOperation() {
    }


    @Pointcut("within(com.example.learnspring.aspect.logic.UnitCalculator+)")
    public void unitOperation() {
    }


    //can use logical operators (&& , ||)
    @Pointcut("arithmeticOperation() || unitOperation()")
    public void loggingAdvanced() {
    }

    @Before("loggingOperation()")
    // "execution( ... ) is an pointcut"
    // @Before is an advice
    // * matches any access modifiers (private, public ..) and any return type
    // (..) matches any nr of arguments
    public void logBefore() {
        log.info("The method add() begins");
    }


    @After("loggingOperation()")
    public void logAfter(final JoinPoint joinPoint) {

        final String name = joinPoint.getSignature().getName();
        log.info("The method {} () end", name);
    }


    @AfterReturning(pointcut = "loggingOperation()",
            returning = "result")
    public void logAfterReturning(final JoinPoint joinPoint,
                                  final Object result) {

        final String name = joinPoint.getSignature().getName();
        log.info("The method {}() ends with {}", name, result);
    }

    @AfterThrowing(pointcut = "execution(* com.example.learnspring.aspect.logic.ArithmeticCalculator.add(..))",
            throwing = "ex")
    public void logAfterThrowing(final JoinPoint joinPoint,
                                 final Throwable ex) {
        var name = joinPoint.getSignature().getName();
        log.error("An exception {} has been thrown in {}()", ex, name);
    }


    // Execute this only when exception is of type IllegalArgumentException
    @AfterThrowing(pointcut = "execution(* com.example.learnspring.aspect.logic.ArithmeticCalculator.add(..))",
            throwing = "ex")
    public void logAfterThrowing2(final JoinPoint joinPoint,
                                  final IllegalArgumentException ex) {
        var name = joinPoint.getSignature().getName();
        log.error("An exception {} has been thrown in {}()", ex, name);
    }


    //Around advice is most powerful , it can doo everything other advices can
    // To use JoinPoint is mandatory to inject ProceedingJoinPoint
    @Around("execution(* com.example.learnspring.aspect.logic.ArithmeticCalculator.add(..))")
    public Object logAround(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        var name = proceedingJoinPoint.getSignature().getName();
        var args = proceedingJoinPoint.getArgs();

        log.info("The method {}() begins with {}.", name, args);

        try {

            var result = proceedingJoinPoint.proceed(); // mandatory to call to continue original method execution
            log.info("The method {}() ends with {}.", name, result);

            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument {} in {}()", args, name);
            throw e;
        }

    }
}
