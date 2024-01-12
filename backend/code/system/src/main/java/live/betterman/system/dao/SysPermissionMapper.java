package live.betterman.system.dao;

import live.betterman.system.model.SysPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhudawei
 * @since 2024-01-10
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {
    @Select("select * from sys_permission")
    List<SysPermission> getAll();
}
