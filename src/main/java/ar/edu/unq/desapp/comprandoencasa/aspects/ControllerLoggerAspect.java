package ar.edu.unq.desapp.comprandoencasa.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
public class ControllerLoggerAspect {
    private final HttpServletRequest request;

    private Logger log = LoggerFactory.getLogger(ControllerLoggerAspect.class);

    @Autowired
    public ControllerLoggerAspect(HttpServletRequest request) {
        this.request = request;
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping) " +
        "|| @annotation(org.springframework.web.bind.annotation.PostMapping) " +
        "|| @annotation(org.springframework.web.bind.annotation.PutMapping)" +
        "|| @annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void restControllers() {
    }

    @Around("restControllers()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            String className = joinPoint.getSignature().getDeclaringTypeName();
            Object[] methodArgs = joinPoint.getArgs();
            String methodName = joinPoint.getSignature().getName();
            Object result = joinPoint.proceed();
            long elapsedTime = System.currentTimeMillis() - start;

            log.info("START - - - - " + LocalDateTime.now().toString() + " - - - -");
            log.info("Endpoint: " + String.valueOf(request.getRequestURL()) + ". HTTP method:" + request.getMethod());
            log.info("Controller: " + className + ". Invoked Method: " + methodName);

            log.debug("Invoked Method Arguments: " + Arrays.toString(methodArgs));
            log.debug("Invocation result: " + result);
            log.debug("Elapsed time: " + elapsedTime + "ms - - - - ");

            log.info("END - - - - " + elapsedTime + "ms - - - - ");

            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument " + Arrays.toString(joinPoint.getArgs()) + " in "
                + joinPoint.getSignature().getName() + "()");
            throw e;
        }
    }
}