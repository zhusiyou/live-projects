package live.betterman.system.service.impl;

import live.betterman.system.model.SysPermission;
import live.betterman.system.dao.SysPermissionMapper;
import live.betterman.system.service.SysPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhudawei
 * @since 2024-01-10
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {
}
