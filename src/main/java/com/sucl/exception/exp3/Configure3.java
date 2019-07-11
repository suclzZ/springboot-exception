package com.sucl.exception.exp3;

import com.sucl.exception.exp.BusException;
import com.sucl.exception.exp.Message;
import com.sucl.exception.exp2.CustomExceptionResolver;
import com.sucl.exception.exp2.SimpleView;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @author sucl
 * @since 2019/7/10
 */
@ConditionalOnProperty(prefix = "exp",name = "exp3",havingValue = "true")
@Configuration
public class Configure3 {

    @ControllerAdvice
    public class ExceptionControllerAdvice {

        @ResponseBody
        @ExceptionHandler(BusException.class)
        public Message handle(BusException e){
//        return e;
            return  new Message() {
                @Override
                public Code getCode() {
                    return e.getCode();
                }

                @Override
                public String getInfo() {
                    return e.getInfo();
                }

                @Override
                public Object getResult() {
                    return null;
                }
            };
        }
    }

}
