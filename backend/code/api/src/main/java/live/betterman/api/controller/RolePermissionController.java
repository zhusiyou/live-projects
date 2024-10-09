package live.betterman.api.controller;

import live.betterman.common.model.Result;
import live.betterman.system.service.SysRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author zhudawei
 * @date 2024-09-26
 */
@RestController
@RequestMapping("/role-permission")
public class RolePermissionController {
    @Autowired
    private SysRolePermissionService rolePermissionService;

    @GetMapping("/roles/{permissionId}")
    public Result<Collection<String>> getRoles(@PathVariable String permissionId) {
        Collection<String> roles = rolePermissionService.getRoles(permissionId);
        return Result.success(roles);
    }

    @GetMapping("/permissions/{roleId}")
    public Result<Collection<String>> getPermissions(@PathVariable String roleId) {
        Collection<String> roles = rolePermissionService.getPermissions(roleId);
        return Result.success(roles);
    }

    @PostMapping("/roles/{permissionId}")
    public Result<Void> saveRoles(@RequestBody Collection<String> roles, @PathVariable String permissionId) {
        return rolePermissionService.saveRoles(roles, permissionId) ? Result.success() : Result.failure();
    }

    @PostMapping("/permissions/{roleId}")
    public Result<Void> savePermissions(@RequestBody Collection<String> permissions, @PathVariable String roleId){
        return rolePermissionService.savePermissions(permissions, roleId) ? Result.success() : Result.failure();
    }
}
