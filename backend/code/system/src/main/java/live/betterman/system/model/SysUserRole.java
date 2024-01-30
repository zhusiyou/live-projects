package live.betterman.system.model;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhudawei
 * @since 2023-12-27
 */
@Data
//@Getter
//@Setter
@EqualsAndHashCode(callSuper = false)
//@Accessors(chain = true, fluent = true)
public class SysUserRole implements Serializable {


    @TableField("user_id")
    private String userId;

    @TableField("role_id")
    private String roleId;

    private String id;


}
