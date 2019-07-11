package com.sucl.exception.exp4;

import com.sucl.exception.exp2.CustomExceptionResolver;
import com.sucl.exception.exp2.SimpleView;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @author sucl
 * @since 2019/7/10
 */
@ConditionalOnProperty(prefix = "exp",name = "exp4",havingValue = "true")
@Configuration
@RestController
public class WebMvcConfigure extends WebMvcConfigurerAdapter {

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
                ErrorPage errorPage400 = new ErrorPage(HttpStatus.BAD_REQUEST,"/err/400");
                ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND,"/err/404");
                ErrorPage errorPage500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/err/500");
                configurableEmbeddedServletContainer.addErrorPages(errorPage400,errorPage404,errorPage500);
            }
        };
    }

//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/err/400").setViewName("err/400");
//        registry.addViewController("/err/404").setViewName("err/404");
//        registry.addViewController("/err/500").setViewName("err/500");
//    }

    @RequestMapping("/err/{code}")
    public String err(@PathVariable("code") String code){
        return "exp4: "+code;
    }
}
