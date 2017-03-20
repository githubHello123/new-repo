package com.carpart.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/3/20.
 */
@Aspect
@Component
public class HttpAspect {
    private static Logger log = LoggerFactory.getLogger(HttpAspect.class);

    @Before("execution(public * com.carpart.controller.HelloController.list(..))")
    public void log(){
        log.info("I have been aspect");
    }
}
