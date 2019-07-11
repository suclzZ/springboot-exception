package com.sucl.exception.exp1;

import com.sun.deploy.net.HttpResponse;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author sucl
 * @since 2019/7/11
 */
@ConditionalOnProperty(prefix = "exp",name = "exp1",havingValue = "true")
@Controller
@RequestMapping
@Configuration
public class Configure1 {

    @Bean
    public ErrorController errorController(ErrorAttributes errorAttributes){
        return  new MyAbstractErrorController(errorAttributes);
    }

    /**
     * 源码中与下面的Controller写在以前，那样就可以拿到errorAttributes中的错误信息，同时可以通过
     * ErrorViewResolver来解析请求获取ModelAndView 的viewName
     */
    public class MyAbstractErrorController extends AbstractErrorController {
        private ErrorAttributes errorAttributes;

        public MyAbstractErrorController(ErrorAttributes errorAttributes) {
            super(errorAttributes);
            this.errorAttributes = errorAttributes;
        }

        @Override
        public String getErrorPath() {
            return "/exp1";
        }
    }

    @RequestMapping("/exp1")
    public void exp1(HttpServletRequest request, HttpServletResponse response){
        try {
            String info = "exp1:";
            response.getWriter().write(info);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
