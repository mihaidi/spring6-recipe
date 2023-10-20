package com.example.learnspring.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StopWatch;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MeasurementInterceptor implements HandlerInterceptor {


    private static final String NAME = "MeasurementInterceptor.TIMER";

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        var sw = new StopWatch();
        sw.start();
        request.setAttribute(NAME, sw);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {

        var timer = (StopWatch) request.getAttribute(NAME);

        timer.stop();

        modelAndView.addObject("processingTime", timer.getTotalTimeMillis());
    }
}
