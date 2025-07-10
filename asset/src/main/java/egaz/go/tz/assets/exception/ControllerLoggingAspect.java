//package egaz.go.tz.assets.exception;
//
//import egaz.go.tz.assets.service.AuditLogService;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.stream.Collectors;
//
//@Aspect
//@Component
//public class ControllerLoggingAspect {
//
//    @Autowired
//    private AuditLogService logService;@Around("execution(* egaz.go.tz.assets.controller..*(..))")
//
//    public Object logControllerMethods(ProceedingJoinPoint joinPoint) throws Throwable {
//        String entityName = joinPoint.getSignature().getDeclaringType().getSimpleName().replace("Controller", "");
//        String method = joinPoint.getSignature().getName();
//        String user = "anonymous"; // or "system" or any default label
//
//
//        Object[] args = joinPoint.getArgs();
//        String requestData = Arrays.stream(args)
//            .map(Object::toString)
//            .collect(Collectors.joining(", "));
//
//        LocalDateTime start = LocalDateTime.now();
//        Object result;
//        try {
//            result = joinPoint.proceed(); // proceed with method call
//        } catch (Exception ex) {
//            logService.auditLogs("ERROR", entityName, null, "Exception in method: " + method + ", reason: " + ex.getMessage(), user);
//            throw ex;
//        }
//
//        logService.auditLogs("ACTION", entityName, null, "Called method: " + method + ", request: " + requestData, user);
//        return result;
//    }
//}
