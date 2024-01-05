package live.betterman.system.dao;

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
    List<SysRole> getRolesByUserId(String userId);
}
