package live.betterman.api.config.security;

import live.betterman.system.service.SysPermissionService;
import live.betterman.system.service.impl.MyUserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author: zhudawei
 * @date: 2024/1/4
 * @description:
 */
@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailServiceImpl myUserDetailServiceImpl;
    @Autowired
    private MobileCodeAuthenticationProvider mobileCodeAuthenticationProvider;
    @Autowired
    private SysPermissionService permissionService;

//    @Override
//    public void configure(WebSecurity webSecurity) throws Exception {
//        webSecurity
//                // 配置不需要spring security管理的url pattern
//                .ignoring()
//                .antMatchers("/test/**");
//    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //将自定义provider加入providerManager
                .authenticationProvider(mobileCodeAuthenticationProvider)
                .authorizeHttpRequests()
                // 配置的白名单仅仅是让Security框架忽略对指定路径的权限校验,并不代表配置了白名单之后指定路径就不走过滤链了
                .mvcMatchers("/test/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .successHandler(new MyAuthenticationSuccessHandler())
                .failureHandler(new MyAuthenticationFailureHandler())
                .and()
                .logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutSuccessUrl("/test/")
        ;
//        // 配置异常处理器
//        http
//                .exceptionHandling()
//                // 认证 时的异常（当用户请求一个受保护的资源，又没登录时触发）
//                .authenticationEntryPoint(myAuthenticationEntryPoint())
//                // 用户访问无权限资源 时的异常（用户登录后，请求一个受保护的资源，又没权限时触发）
//                .accessDeniedHandler(myAccessDeniedHandler());

        http.addFilterBefore(getMobileCodeFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(filterSecurityInterceptor(), FilterSecurityInterceptor.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder
                .userDetailsService(myUserDetailServiceImpl)
                // 如果不需要对密码进行编码: 设置passwordEncode 或者 设置数据库的密码为"{noop}xxxx"格式
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
        ;
    }

    private MobileCodeAuthenticationFilter getMobileCodeFilter() throws Exception {
        MobileCodeAuthenticationFilter filter = new MobileCodeAuthenticationFilter("/login/mobile");
        filter.setAuthenticationSuccessHandler(new MyAuthenticationSuccessHandler());
        filter.setAuthenticationFailureHandler(new MyAuthenticationFailureHandler());
        filter.setAuthenticationManager(getAuthenticationManager());
        return filter;
    }

    private AuthenticationManager getAuthenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }

//    /**
//     * 自定义 metadataSource 与 access-decision-manager 的授权过滤器
//     * Filter不作为Bean注入,而是在Security配置中使用new的方式让Security来管理
//     * 如果Filter作为Bean注入后，Spring会自动将其管理，即使在Security中配置了忽略，Spring还是照样会将请求使用过滤器进行过滤
//     * @return
//     */
//    private MyFilterSecurityInterceptor myFilterSecurityInterceptor(){
//        MyFilterInvocationSecurityMetadataSource metadataSource = new MyFilterInvocationSecurityMetadataSource(permissionService);
//        MyAccessDecisionManager decisionManager = new MyAccessDecisionManager();
//        MyFilterSecurityInterceptor interceptor = new MyFilterSecurityInterceptor(metadataSource, decisionManager);
//        interceptor.setRejectPublicInvocations(true);
//        return interceptor;
//    }

    private FilterSecurityInterceptor filterSecurityInterceptor(){
        MyFilterInvocationSecurityMetadataSource metadataSource = new MyFilterInvocationSecurityMetadataSource(permissionService);
        MyAccessDecisionManager decisionManager = new MyAccessDecisionManager();

        FilterSecurityInterceptor interceptor = new FilterSecurityInterceptor();
        interceptor.setSecurityMetadataSource(metadataSource);
        interceptor.setAccessDecisionManager(decisionManager);
        //rejectPublicInvocations属性含义为拒绝公共请求， 默认为false。
        // 如果从配置好的SecurityMetadataSource中获取不到当前 request 所对应的ConfigAttribute 时，即认为当前请求为公共请求。
        // 如配置rejectPublicInvocations属性为true，则系统会抛出IllegalArgumentException异常。即当前请求需要配置权限信息。
        interceptor.setRejectPublicInvocations(false);
        return interceptor;
    }

//    @Bean
//    public MyAccessDeniedHandler myAccessDeniedHandler(){
//        return new MyAccessDeniedHandler();
//    }
//    @Bean
//    public MyAuthenticationEntryPoint myAuthenticationEntryPoint(){
//        return new MyAuthenticationEntryPoint();
//    }
}
