package live.betterman.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @author: zhudawei
 * @date: 2024/1/23
 * @description:
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        //registry.addViewController("/").setViewName("forward:/dashboard");
//        //registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
//    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
//                .allowedOriginPatterns("*")
                .allowedMethods("GET","HEAD","POST","PUT","DELETE","OPTIONS")
                .allowCredentials(false)
                .maxAge(3600)
                .allowedHeaders("*");
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // 多个拦截器组成一个拦截器链
//        // addPathPatterns 用于添加拦截规则
//        // excludePathPatterns 用户排除拦截
//        registry.addInterceptor(new ResponseResultInterceptor()).addPathPatterns("/**");
//        //配置租户拦截器
//        registry.addInterceptor(new TenantInterceptor()).addPathPatterns("/**");
//        //调用存储过程增加日志
//        registry.addInterceptor(new CallLogInterceptor()).addPathPatterns("/**");
//    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
//    }

//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        //为了在restController接收参数时，对日期支持多种格式
//        registry.addConverter(new DateConverter());
//    }

    /**
     * jackson反序列化时加入对日期支持多种格式
     * @return
     */
//    @Bean
//    public MappingJackson2HttpMessageConverter MappingJsonpHttpMessageConverter() {
//        ObjectMapper mapper = new ObjectMapper();
//
//        // ObjectMapper为了保障线程安全性，里面的配置类都是一个不可变的对象
//        // 所以这里的setDateFormat的内部原理其实是创建了一个新的配置类
//        DateFormat dateFormat = mapper.getDateFormat();
//        mapper.setDateFormat(new MyDateFormat(dateFormat));
//
//        // 设置在反序列化时忽略在JSON字符串中存在，而在Java中不存在的属性
//        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(mapper);
//        return converter;
//    }
}
