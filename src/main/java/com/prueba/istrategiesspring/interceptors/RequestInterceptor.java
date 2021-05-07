package com.prueba.istrategiesspring.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Slf4j
@Component
public class RequestInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("startTime", LocalDateTime.now());
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        log.info(request.getMethod() + " - " + request.getRequestURI() + " | " + "Status: " + response.getStatus() + " - Start Time: " + request.getAttribute("startTime") + " End Time: " + LocalDateTime.now());
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
