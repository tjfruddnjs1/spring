package com.spring.socket.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class ControllerNameInterceptor implements HandlerInterceptor {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);

        String controllerName = "";

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            controllerName = handlerMethod.getBeanType().getSimpleName().replace("Controller", "");
        }

        String queryString = request.getQueryString() != null ? "?" + request.getQueryString() : "";
        request.setAttribute("queryString", queryString);
        request.setAttribute("controllerName", controllerName);
    }
}
