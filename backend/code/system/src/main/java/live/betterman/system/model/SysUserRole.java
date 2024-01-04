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
public class SysUserRole implements Serializable {


    @TableField("user_id")
    private String userId;

    @TableField("role_id")
    private String roleId;

    private String id;


}
