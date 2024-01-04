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
public class SysMenu implements Serializable {


    @TableId("menu_id")
    private String menuId;

    @TableField("menu_name")
    private String menuName;

    @TableField("component_path")
    private String componentPath;

    private String url;


}
