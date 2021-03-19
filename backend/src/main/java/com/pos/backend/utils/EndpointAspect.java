package com.pos.backend.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(1)
@Component
@ConditionalOnExpression("${endpoint.aspect.enabled:true}")
public class EndpointAspect {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	 /**
     * Pointcut that matches all repositories, services and Web REST endpoints.
     */
    @Pointcut("within(@org.springframework.stereotype.Repository *)" +
        " || within(@org.springframework.stereotype.Service *)" +
        " || within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanPointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    /**
     * Pointcut that matches all Spring beans in the application's main packages.
     */
    @Pointcut("within(com.pos.backend.controller..*)" +
        " || within(com.pos.backend.service..*)" +
        " || within(com.pos.backend.dao..*)")
    public void applicationPackagePointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

	@Before(value = ("applicationPackagePointcut() && springBeanPointcut()"))
	public void endpointBefore(JoinPoint p) {
		if (log.isTraceEnabled()) {
			log.trace(p.getTarget().getClass().getSimpleName() + " " + p.getSignature().getName() + " START");
		}
	}

	@AfterReturning(value = ("applicationPackagePointcut() && springBeanPointcut()"), returning = "returnValue")
	public void endpointAfterReturning(JoinPoint p, Object returnValue) {
		if (log.isTraceEnabled()) {
			log.trace(p.getTarget().getClass().getSimpleName() + " " + p.getSignature().getName() + " END");
		}
	}

	@AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()", throwing = "e")
	public void endpointAfterThrowing(JoinPoint p, Exception e) throws Exception {
		if (log.isTraceEnabled()) {
			log.error(
					p.getTarget().getClass().getSimpleName() + " " + p.getSignature().getName() + " " + e.getMessage());
		}
	}
}