package live.betterman.api.config.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * @author: zhudawei
 * @date: 2024/1/9
 * @description: 手机号验证码 Authentication
 */
public class MobileCodeAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private String code;
    public String getCode(){
        return code;
    }

    private String companyId;
    public String getCompanyId(){
        return companyId;
    }

    public MobileCodeAuthenticationToken(String username, String code, String companyId){
        super(username, code);
        this.code = code;
        this.companyId = companyId;
    }
}
