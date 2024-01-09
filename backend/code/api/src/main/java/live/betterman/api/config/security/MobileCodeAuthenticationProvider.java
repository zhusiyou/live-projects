package live.betterman.api.config.security;

import live.betterman.system.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * @author: zhudawei
 * @date: 2024/1/9
 * @description: 使用手机号验证码认证
 */
@Component
public class MobileCodeAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    @Autowired
    private MyUserDetailService myUserDetailService;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        MobileCodeAuthenticationToken authenticationToken = (MobileCodeAuthenticationToken)authentication;
        String code = authenticationToken.getCode();
        String companyId = authenticationToken.getCompanyId();
        //code 验证码可以从redis或者其他地方获取
        if(!code.equals("haha") || !companyId.equals("1")){
            throw new InternalAuthenticationServiceException("手机号验证号验证失败");
        }
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        UserDetails userDetails = myUserDetailService.loadUserByMobile(username);
        return userDetails;
    }

    /**
     * 指定此provider处理的authentication类型
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication){
        return MobileCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
