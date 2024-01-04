package live.betterman.api.controller;

import io.swagger.annotations.Api;
import live.betterman.system.model.SysUser;
import live.betterman.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: zhudawei
 * @date: 2023/12/27
 * @description:
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户")
public class UserController {
    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/list")
    public List<SysUser> getUsers(){
        return sysUserService.list();
    }

    @GetMapping("/current")
    public Authentication currentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User principal = (User) authentication.getPrincipal();
        System.out.println("身份：" + principal.getUsername());
        System.out.println("凭证：" + authentication.getCredentials());
        System.out.println("权限：" + authentication.getAuthorities());
        return authentication;
    }

    /**
     * 多线程下可访问登录用户信息
     * 需要在启动参数中加入 -Dspring.security.strategy=MODE_INHERITABLETHREADLOCAL
     * @return
     */
    @GetMapping("/current/inheritable")
    public String currentUserInheritableThreadLocal(){
        new Thread(()->{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User principal = (User) authentication.getPrincipal();
            System.out.println("身份：" + principal.getUsername());
            System.out.println("凭证：" + authentication.getCredentials());
            System.out.println("权限：" + authentication.getAuthorities());
        }).start();
        return "haha security";
    }
}
