package live.betterman.api.controller;

import io.swagger.annotations.Api;
import live.betterman.system.model.SysUser;
import live.betterman.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
