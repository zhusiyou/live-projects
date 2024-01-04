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
public class SysUser implements Serializable {


    @TableId("user_id")
    private String userId;

    @TableField("user_name")
    private String userName;

    private String password;


}
