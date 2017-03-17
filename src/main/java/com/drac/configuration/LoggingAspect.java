package com.drac.configuration;


import com.drac.controller.PatientController;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by anil on 3/16/17.
 */
@Aspect
@Component
public class LoggingAspect {
    public static final Logger logger = LoggerFactory.getLogger(PatientController.class);

    @Before("within(com.drac.*.*)")
    public void logMethodArguments(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        Object[] arguments = jp.getArgs();

        logger.info("Method: {} called with arguments: {}", methodName, Arrays.toString(arguments));
    }
}
