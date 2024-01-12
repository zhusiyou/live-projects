package live.betterman.system.service;

import live.betterman.system.model.SysPermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhudawei
 * @since 2024-01-10
 */
public interface SysPermissionService extends IService<SysPermission> {
    List<SysPermission> getAll();
}
