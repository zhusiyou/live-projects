package live.betterman.api.controller;

import live.betterman.common.model.Result;
import live.betterman.system.model.SysPermission;
import live.betterman.system.model.SysRolePermission;
import live.betterman.system.service.SysPermissionService;
import live.betterman.system.service.SysRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: zhudawei
 * @date: 2024/1/11
 * @description:
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private SysPermissionService permissionService;

    @GetMapping("/")
    public String needPermission(){
        return "need permission!";
    }

    @PostMapping("/add")
    public Result<Boolean> addPermission(@RequestBody SysPermission permission) {
        boolean result = permissionService.saveOrUpdate(permission);
        return Result.success(result);
    }

    @PostMapping("/edit")
    public Result<Boolean> editPermission(@RequestBody SysPermission permission) {
        boolean result = permissionService.saveOrUpdate(permission);
        return Result.success(result);
    }

    @GetMapping("/list")
    public Result<List<SysPermission>> list() {
        List<SysPermission> list = permissionService.list();
        return Result.success(list);
    }
}
