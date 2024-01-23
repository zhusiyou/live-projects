package live.betterman.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import live.betterman.system.model.SysUserRole;
import live.betterman.system.dao.SysUserRoleMapper;
import live.betterman.system.service.SysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhudawei
 * @since 2023-12-27
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    @Override
    public boolean save(String userId, List<String> roleIds) {
        // 删除当前用户的角色配置
        LambdaQueryWrapper<SysUserRole> deleteQuery = new LambdaQueryWrapper<SysUserRole>()
                .eq(SysUserRole::userId, userId);
        baseMapper.delete(deleteQuery);

        List<SysUserRole> configs = roleIds.stream().map(item -> new SysUserRole().roleId(item).userId(userId))
                .collect(Collectors.toList());

        //TODO: zhudawei 广播？
        return this.saveBatch(configs);
    }

}
