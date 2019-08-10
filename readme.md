 [参考](https://www.cnblogs.com/FlyAway2013/p/7944568.html)
 
 1、默认情况下springboot异常处理在ErrorMvcAutoConfiguration配置，主要是通过其内置的tomcat完成，针对内部异常，将错误指向/error->BasicErrorController进行处理，
 但是要注意，同时通过BeanNameViewer这个视图解析器来完成error最终的显示。要注意的是，这个处理内部异常；基于内置tomcat异常。
 2、通过继承AbstractErrorController可以覆盖BasicErrorController，改写其默认逻辑
 3、通过重写ErrorAttributes可以在BasicErrorController的处理逻辑上修改返回值
 4、通过实现HandlerExceptionResolver可以覆盖默认的异常处理器
 5、通过@ControllerAdvice可以实现Controller的异常统一处理
 6、通过注册EmbeddedServletContainerCustomizer可以根据不同响应错误定位不同处理页面比如404、500...
 