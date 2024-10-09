package live.betterman.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import live.betterman.system.model.SysRolePermission;
import live.betterman.system.dao.SysRolePermissionMapper;
import live.betterman.system.service.SysRolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhudawei
 * @since 2024-01-10
 */
@Service
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements SysRolePermissionService {

    @Override
    public Collection<String> getRoles(String permissionId) {
        if(StrUtil.isEmpty(permissionId)){
            return Collections.emptySet();
        }
        LambdaQueryWrapper<SysRolePermission> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysRolePermission::getPermissionId, permissionId);
        return list(wrapper).stream().map(SysRolePermission::getRoleId).collect(Collectors.toSet());
    }

    @Override
    public Collection<String> getPermissions(String roleId) {
        if(StrUtil.isEmpty(roleId)){
            return Collections.emptySet();
        }
        LambdaQueryWrapper<SysRolePermission> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysRolePermission::getRoleId, roleId);
        return list(wrapper).stream().map(SysRolePermission::getPermissionId).collect(Collectors.toSet());
    }

    @Override
    public boolean saveRoles(Collection<String> roles, String permissionId) {
        LambdaQueryWrapper<SysRolePermission> wrapper = Wrappers.lambdaQuery(SysRolePermission.class)
                .eq(SysRolePermission::getPermissionId, permissionId);
        remove(wrapper);
        if(!roles.isEmpty()){
            Set<SysRolePermission> entities = roles.stream().distinct().map(item -> {
                SysRolePermission entity = new SysRolePermission();
                entity.setRoleId(item);
                entity.setPermissionId(permissionId);
                return entity;
            }).collect(Collectors.toSet());
            return saveBatch(entities);
        }
        return false;
    }

    @Override
    public boolean savePermissions(Collection<String> permissions, String roleId) {
        LambdaQueryWrapper<SysRolePermission> wrapper = Wrappers.lambdaQuery(SysRolePermission.class)
                .eq(SysRolePermission::getRoleId, roleId);
        remove(wrapper);
        if (!permissions.isEmpty()) {
            Set<SysRolePermission> entities = permissions.stream().distinct().map(item -> {
                SysRolePermission entity = new SysRolePermission();
                entity.setRoleId(item);
                entity.setPermissionId(roleId);
                return entity;
            }).collect(Collectors.toSet());
            return saveBatch(entities);
        }
        return false;
    }
}
