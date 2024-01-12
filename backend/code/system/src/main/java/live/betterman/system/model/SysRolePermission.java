package live.betterman.system.model;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhudawei
 * @since 2024-01-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysRolePermission implements Serializable {


    @TableField("permission_id")
    private String permissionId;

    @TableField("role_id")
    private String roleId;

    private String id;


}
