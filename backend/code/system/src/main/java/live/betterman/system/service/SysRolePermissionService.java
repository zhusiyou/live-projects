package live.betterman.system.service;

import live.betterman.system.model.SysRolePermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhudawei
 * @since 2024-01-10
 */
public interface SysRolePermissionService extends IService<SysRolePermission> {
    Collection<String> getRoles(String permissionId);
    Collection<String> getPermissions(String roleId);
    boolean saveRoles(Collection<String> roles, String permissionId);
    boolean savePermissions(Collection<String> permissions, String roleId);
}
