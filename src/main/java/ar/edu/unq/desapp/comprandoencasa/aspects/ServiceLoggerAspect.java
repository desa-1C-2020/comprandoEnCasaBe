package ar.edu.unq.desapp.comprandoencasa.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ServiceLoggerAspect {
    private Logger log = LoggerFactory.getLogger(ServiceLoggerAspect.class);

    @Pointcut("execution(public * ar.edu.unq.desapp.comprandoencasa.service.*.*(..))")
    public void serviceMethodInvoked() {
        // This constructor is intentionally empty. Nothing special is needed here.
    }

    @Around("serviceMethodInvoked()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            String className = joinPoint.getSignature().getDeclaringTypeName();
            Object[] methodArgs = joinPoint.getArgs();
            String methodName = joinPoint.getSignature().getName();
            Object result = joinPoint.proceed();
            long elapsedTime = System.currentTimeMillis() - start;

            log.info("START - - - - - - - -");
            log.info("Service: " + className + ". Invoked Method: " + methodName);

            log.debug("Invoked Method Arguments: " + Arrays.toString(methodArgs));
            log.debug("Invocation result: " + result);
            log.debug("Elapsed time: " + elapsedTime + "ms - - - - ");

            log.info("END - - - - - - - - ");

            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument " + Arrays.toString(joinPoint.getArgs()) + " in "
                + joinPoint.getSignature().getName() + "()");
            throw e;
        }
    }
}