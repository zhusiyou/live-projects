package live.betterman.system.dao;

import live.betterman.system.model.SysPermission;
import live.betterman.system.model.SysRole;
import live.betterman.system.model.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhudawei
 * @since 2023-12-27
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    SysUser loadByUsername(String username);

    /**
     * 获取指定用户的角色
     * @param userId
     * @return
     */
    List<SysRole> getRolesByUserId(String userId);

    /**
     * 获取指定用户的权限
     * @param userId
     * @return
     */
    List<SysPermission> getPermissionsByUserId(String userId);
}
