package live.betterman.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zhudawei
 * @date: 2024/1/9
 * @description: 登录接口
 */
@Api(tags = "登录接口")
@RestController
@RequestMapping("/login")
public class LoginController {

    /**
     * 手机号验证码登录
     * 此路由会由 MobileCodeAuthenticationFilter 处理， 并交给 MobileCodeAuthenticationProvider 完成认证
     * @param mobile 手机号
     * @param code 验证码
     * @param companyId 分公司id
     * @return
     */
    @ApiOperation("手机号验证码登录")
    @GetMapping("/mobile")
    public String byMobile(String mobile, String code, String companyId){
        // 这个return语句不会被执行，返回结果由 MobileCodeAuthenticationFilter#setAuthenticationSuccessHandler 处理
        return "success";
    }
}
