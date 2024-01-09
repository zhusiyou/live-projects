package live.betterman.api.config.security;

import live.betterman.system.service.impl.MyUserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author: zhudawei
 * @date: 2024/1/4
 * @description:
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailServiceImpl myUserDetailServiceImpl;
    @Autowired
    private MobileCodeAuthenticationProvider mobileCodeAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //将自定义provider加入providerManager
                .authenticationProvider(mobileCodeAuthenticationProvider)
                .authorizeHttpRequests()
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
        http.addFilterBefore(getMobileCodeFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder
                .userDetailsService(myUserDetailServiceImpl)
                // 如果不需要对密码进行编码: 设置passwordEncode 或者 设置数据库的密码为"{noop}xxxx"格式
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
        ;
    }

    @Bean
    public MobileCodeAuthenticationFilter getMobileCodeFilter() throws Exception {
        MobileCodeAuthenticationFilter filter = new MobileCodeAuthenticationFilter("/login/mobile");
        filter.setAuthenticationSuccessHandler(new MyAuthenticationSuccessHandler());
        filter.setAuthenticationFailureHandler(new MyAuthenticationFailureHandler());
        filter.setAuthenticationManager(getAuthenticationManager());
        return filter;
    }

    @Bean
    public AuthenticationManager getAuthenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }
}
