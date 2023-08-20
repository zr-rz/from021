package com.example.spring.aop.aspectj;

import com.example.spring.aop.Pointcut;
import com.example.spring.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * aspectJ表达式的advisor
 *
 * @author ryan
 * @date 2023/6/18 21:44
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    private AspectJExpressionPointcut pointcut;

    private Advice advice;

    private String expression;

    public void setExpression(String expression) {
        this.expression = expression;
        pointcut = new AspectJExpressionPointcut(expression);
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    @Override
    public Pointcut getPointcut() {
        if (pointcut == null) {
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }
}
