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
 * @since 2024-01-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysPermission implements Serializable {


    @TableId("permission_id")
    private String permissionId;

    @TableField("permission_name")
    private String permissionName;

    private String url;


}
