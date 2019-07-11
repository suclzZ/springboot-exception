package com.sucl.exception.web;

import com.sucl.exception.exp.BusException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.util.List;

/**
 * @author sucl
 * @since 2019/7/10
 */
@RestController
@RequestMapping
public class HelloController {

    public HelloController(List<HandlerExceptionResolver> handlerExceptionResolvers){
        System.out.println();
    }

    @RequestMapping
    public String hello(@RequestParam(value = "name",required = false) String name){
        return "hello ".concat(name).concat("!");
    }

    @RequestMapping("/exp")
    public String exp() throws Exception{
        throw new BusException("调用异常！");
    }

}
