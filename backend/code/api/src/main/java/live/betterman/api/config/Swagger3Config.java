package live.betterman.api.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.plugins.WebFluxRequestHandlerProvider;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * http://localhost:8080/swagger-ui/index.html
 */
@Configuration
//@ConditionalOnProperty(prefix = "knife4j",name="enable",havingValue = "true")
public class Swagger3Config {
    @Bean
    public Docket createRestApi() {
        //添加head参数start
//        List<RequestParameter> pars = new ArrayList<RequestParameter>();
//        RequestParameterBuilder tokenPar = new RequestParameterBuilder();
//        tokenPar.name("Authorization").description("令牌").in(ParameterType.HEADER).required(false);
//        RequestParameterBuilder tenantId = new RequestParameterBuilder();
//        tenantId.name("tenant_id").description("租户ID").in(ParameterType.HEADER).required(false);
//        pars.add(tokenPar.build());
//        pars.add(tenantId.build());
        //添加head参数end

        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
//                .globalRequestParameters(pars)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("项目接口文档")
                .description("项目接口文档")
                .contact(new Contact("icode", "http://icode.betterman.live", "zhudw@betterman.live"))
                .version("1.0")
                .build();
    }

//    /**
//     * 解决：升级到springboot 2.6.x 后，启动项目报空指针问题
//     * 在application.yml配置了spring.mvc.pathmatch.matching-strategy: ant_path_matcher 后不需要这个了
//     * see: https://blog.csdn.net/zqlwcx/article/details/126729848
//     */
//    @Bean
//    public static BeanPostProcessor springfoxHandlerProviderBeanPostProcessor() {
//        return new BeanPostProcessor() {
//
//            @Override
//            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//                if (bean instanceof WebMvcRequestHandlerProvider || bean instanceof WebFluxRequestHandlerProvider) {
//                    customizeSpringfoxHandlerMappings(getHandlerMappings(bean));
//                }
//                return bean;
//            }
//
//            private <T extends RequestMappingInfoHandlerMapping> void customizeSpringfoxHandlerMappings(List<T> mappings) {
//                List<T> copy = mappings.stream()
//                        .filter(mapping -> mapping.getPatternParser() == null)
//                        .collect(Collectors.toList());
//                mappings.clear();
//                mappings.addAll(copy);
//            }
//
//            @SuppressWarnings("unchecked")
//            private List<RequestMappingInfoHandlerMapping> getHandlerMappings(Object bean) {
//                try {
//                    Field field = ReflectionUtils.findField(bean.getClass(), "handlerMappings");
//                    field.setAccessible(true);
//                    return (List<RequestMappingInfoHandlerMapping>) field.get(bean);
//                } catch (IllegalArgumentException | IllegalAccessException e) {
//                    throw new IllegalStateException(e);
//                }
//            }
//        };
//    }
}
