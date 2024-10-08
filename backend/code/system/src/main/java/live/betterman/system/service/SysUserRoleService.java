package live.betterman.system.service;

import live.betterman.system.model.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhudawei
 * @since 2023-12-27
 */
public interface SysUserRoleService extends IService<SysUserRole> {
    boolean save(String userId, List<String> roleIds);
    List<SysUserRole> getByUserId(String userId);
}
