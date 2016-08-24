package com.tuitanews.core;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class ExceptionHandler implements HandlerExceptionResolver {  
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);
    public ModelAndView resolveException(HttpServletRequest request,  
            HttpServletResponse response, Object handler, Exception ex) {
        LOGGER.error("", ex);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("error", ex.toString());
        return new ModelAndView("/error/error", map);
    }  
  
}
