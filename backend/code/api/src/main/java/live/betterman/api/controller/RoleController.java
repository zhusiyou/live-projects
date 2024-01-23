package live.betterman.api.controller;

import io.swagger.annotations.Api;
import live.betterman.common.model.Result;
import live.betterman.system.model.SysRole;
import live.betterman.system.model.SysUser;
import live.betterman.system.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zhudawei
 * @date: 2024/1/22
 * @description:
 */
@RestController
@RequestMapping("/role")
@Api(tags = "角色")
public class RoleController {
    @Autowired
    private SysRoleService roleService;

    @PostMapping("/add")
    public Result<Boolean> addUser(@RequestBody SysRole role){
        boolean result = roleService.saveOrUpdate(role);
        return Result.success(result);
    }
    @PostMapping("/edit")
    public Result<Boolean> editUser(@RequestBody SysRole role){
        boolean result = roleService.saveOrUpdate(role);
        return Result.success(result);
    }
}
