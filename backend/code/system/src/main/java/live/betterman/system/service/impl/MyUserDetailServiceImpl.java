package live.betterman.system.service.impl;

import live.betterman.system.dao.SysUserMapper;
import live.betterman.system.model.SysRole;
import live.betterman.system.model.SysUser;
import live.betterman.system.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author: zhudawei
 * @date: 2024/1/5
 * @description:
 */
@Component
public class MyUserDetailServiceImpl implements MyUserDetailService, UserDetailsService {
    @Autowired
    private SysUserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = userMapper.loadByUsername(username);
        List<SysRole> roles = userMapper.getRolesByUserId(sysUser.getUserId());
        sysUser.setRoles(roles);
        return sysUser;
    }

    @Override
    public UserDetails loadUserByMobile(String mobile){
        SysUser sysUser = new SysUser();
        sysUser.setUserId("1");
        sysUser.setUserName(mobile);

        SysRole sysRole = new SysRole();
        sysRole.setRoleId("role_1");
        sysRole.setRoleName("admin");
        sysUser.setRoles(Arrays.asList(sysRole));

        return sysUser;
    }
}
