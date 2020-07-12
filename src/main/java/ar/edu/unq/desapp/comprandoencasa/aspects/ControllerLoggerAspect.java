package ar.edu.unq.desapp.comprandoencasa.aspects;

import ar.edu.unq.desapp.comprandoencasa.security.UserPrincipal;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

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
    private void restControllers() {
        // This constructor is intentionally empty. Nothing special is needed here.
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
            Optional<UserPrincipal> userId = getUserId(methodArgs);

            log.info("START - - - - - - - -");
            log.info("Endpoint: " + String.valueOf(request.getRequestURL()) + ". HTTP method:" + request.getMethod());
            log.info("Controller: " + className + ". Invoked Method: " + methodName);
            userId.ifPresent(userPrincipal -> log.info("User: " + userPrincipal.getId() + "."));

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

    private Optional getUserId(Object[] methodArgs) {
        ArrayList argsList = new ArrayList(Arrays.asList(methodArgs));
        return argsList
            .stream()
            .filter(arg -> arg instanceof UserPrincipal)
            .map(userPrincipal -> (UserPrincipal) userPrincipal)
            .findFirst();
    }
}