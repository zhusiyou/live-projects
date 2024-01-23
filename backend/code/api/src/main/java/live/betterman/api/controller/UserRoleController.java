package live.betterman.api.controller;

import io.swagger.annotations.Api;
import live.betterman.common.model.Result;
import live.betterman.system.model.SysRole;
import live.betterman.system.model.SysUserRole;
import live.betterman.system.service.SysRoleService;
import live.betterman.system.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: zhudawei
 * @date: 2024/1/22
 * @description:
 */
@RestController
@RequestMapping("/user-role")
@Api(tags = "用户角色")
public class UserRoleController {
    @Autowired
    private SysUserRoleService userRoleService;

//    @PostMapping("/add")
//    public Result<Boolean> add(@RequestBody List<SysUserRole> configs){
//        boolean result = userRoleService.saveBatch(configs);
//        return Result.success(result);
//    }

    @PostMapping("/user/{userId}")
    public Result<Boolean> edit(@RequestBody List<String> roleIds, @PathParam("userId") String userId){
        boolean result = userRoleService.save(userId, roleIds);
        return Result.success(result);
    }
}
