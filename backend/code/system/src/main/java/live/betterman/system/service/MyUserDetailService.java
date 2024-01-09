package live.betterman.system.service;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author: zhudawei
 * @date: 2024/1/9
 * @description:
 */
public interface MyUserDetailService {
    /**
     * 获取指定手机号码的用户信息
     * @param mobile 手机号码
     * @return
     */
    UserDetails loadUserByMobile(String mobile);
}
