package com.sucl.exception.exp2;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * 1、业务异常(/exp 自己抛出异常)
 * 2、非业务异常(/abc 不存在的路径) 走exp1模式
 *
 * @author sucl
 * @since 2019/7/10
 */
@ConditionalOnProperty(prefix = "exp",name = "exp2",havingValue = "true")
@Configuration
public class Configure2 extends WebMvcConfigurerAdapter {

    /**
     * 添加自定义异常处理，会覆盖默认的
     * @param exceptionResolvers
     */
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(handlerExceptionResolver());
    }

    /**
     * 保留默认的异常处理器
     * @param exceptionResolvers
     */
//    @Override
//    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
//        super.extendHandlerExceptionResolvers(exceptionResolvers);
//        exceptionResolvers.add(handlerExceptionResolver());
//    }

    public HandlerExceptionResolver handlerExceptionResolver(){
        return new CustomExceptionResolver();
    }

    @Bean("exp")
    public View view(){
        return new SimpleView("exp2 : ${inof}");
    }
}
