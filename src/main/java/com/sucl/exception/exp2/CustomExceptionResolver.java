package com.sucl.exception.exp2;

import com.sucl.exception.exp.BusException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author sucl
 * @since 2019/7/10
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView modelAndView = new ModelAndView("exp");//交个SimpleView去处理了
        modelAndView.addObject("inof",ex.getMessage());
        return modelAndView;
    }
}
