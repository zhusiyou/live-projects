package live.betterman.system.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class SysRole implements Serializable {


    @TableId("role_id")
    private String roleId;

    @TableField("role_name")
    private String roleName;


}
