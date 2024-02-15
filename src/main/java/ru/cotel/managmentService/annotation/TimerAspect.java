package ru.cotel.managmentService.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TimerAspect {

    @Pointcut("within(@ru.cotel.managmentService.annotation.Timer *)")
    public void beansAnnotatedWith() {
    }

    @Pointcut("@annotation(ru.cotel.managmentService.annotation.Timer)")
    public void methodsAnnotatedWith(){
    }

    @Around("beansAnnotatedWith() || methodsAnnotatedWith()")
    public Object timerAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long end = System.currentTimeMillis() - start;

        log.info("{} - {} #{}", joinPoint.getTarget().getClass(), joinPoint.getSignature().getName(), end);
        return result;
    }

}
