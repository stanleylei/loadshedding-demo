package com.giganobs.loadsheddingdemo;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class LogExecutionTimeAspect {

    private final Timer sampleTimer;

    public LogExecutionTimeAspect(MeterRegistry meterRegistry) {
        // Creating a timer metric
        this.sampleTimer = meterRegistry.timer("method.execution.time");
    }

    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        try {
            return joinPoint.proceed();
        } finally {
            long executionTime = System.nanoTime() - start;
            // Log method execution time
            System.out.println(joinPoint.getSignature() + " executed in " + executionTime / 1_000_000 + "ms");
            // Record method execution time
            sampleTimer.record(executionTime, TimeUnit.NANOSECONDS);
        }
    }
}