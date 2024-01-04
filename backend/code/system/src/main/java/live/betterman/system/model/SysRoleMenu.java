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
 * @since 2023-12-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysRoleMenu implements Serializable {


    @TableField("menu_id")
    private String menuId;

    @TableField("role_id")
    private String roleId;

    private String id;


}
