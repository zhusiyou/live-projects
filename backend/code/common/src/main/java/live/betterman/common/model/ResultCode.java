package live.betterman.common.model;

import lombok.Getter;
/**
 * @author: zhudawei
 * @date: 2023/12/27
 * @description: API响应结果code
 */
@Getter
public enum ResultCode {

    SUCCESS(200, "成功"),
    FAILURE(100000, "失败"),
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "认证失败"),
    ACCESS_DENIED(403, "没有访问权限"),
    NOT_FOUND(404, "接口不存在"),
    INTERNAL_SERVER_ERROR(500, "系统繁忙"),
    METHOD_NOT_ALLOWED(405, "方法不被允许"),

    LOGIN_FAILURE(1200001, "账号或密码错误。请重试"),

    /*参数错误:1001-1999*/
    PARAMS_IS_INVALID(1001, "参数无效"),
    PARAMS_IS_BLANK(1002, "参数为空"),
    PARAMS_DATA_IS_NOT_FOUND(1003, "未找到记录"),
    PARAMS_VERSION_IS_OUT(1004, "当前数据发生变化，请刷新后重试"),

    INVALID_ARGUMENT(777, "参数无效"),

    // 角色
    ROLE_NOT_FOUND(40004, "角色不存在"),
    ROLE_NAME_EXIST(40014, "角色名称已存在"),
    ROLE_CODE_EXIST(40024, "角色编码已存在"),

    // 菜单
    MENU_SUB_NAME_EXIST(900001, "同级菜单名称不能重复"),
    MENU_NOT_FOUND(900002, "没有此信息"),
    MENU_HAVE_CHILDREN(900003, "存在子菜单"),
    MENU_HAVE_ROLE(900004, "有角色包含此菜单,要解除所有关联后才能删除。"),

    //流程相关
    PROC_TASK_COMPLETED(1600001,"当前状态已发生变化，请返回上一级进行处理。"),
    PROC_TASK_IS_SUSPENDED(1600002,"任务已挂起，请稍后操作！"),
    PROC_DEF_ID_NOT_EMPTY(1700001,"流程定义ID不能为空"),
    ACT_ID_NOT_EMPTY(1700051,"流程节点ID不能为空"),
    FALL_BACK_FAILURE(180001,"流程回退失败"),
    FIRST_BACK_FAILURE(180002,"流程驳回失败"),
    FINISH_END_FAILURE(180003,"流程结束失败"),
    REPORT_START_FAILURE(180010,"报备流程启动失败！请退出后重新操作！"),
    REPORT_CHCK_FAILURE(180011,"审核报备任务失败！请退出后重新操作！"),
    REPORT_SEE_START_FAILURE(180012,"带看流程启动失败！请退出后重新操作！"),
    REPORT_SEE_UPDATE_FAILURE(180013,"修改带看任务失败！请退出后重新操作！"),
    REPORT_SEE_CHECK_FAILURE(180014,"审核带看任务失败！请退出后重新操作！"),
    SALE_START_FAILURE(180020,"当前状态已发生变化，请返回上一级进行处理。"),
    SALE_BUY_CHCK_FAILURE(180021,"当前状态已发生变化，请返回上一级进行处理。"),
    SALE_DRAFT_CHCK_FAILURE(180022,"当前状态已发生变化，请返回上一级进行处理。"),
    SALE_NETSIGNATURE_CHCK_FAILURE(180023,"当前状态已发生变化，请返回上一级进行处理。"),
    SALE_SELL_CHCK_FAILURE(180024,"当前状态已发生变化，请返回上一级进行处理。"),
    SALE_BUY_UPDATE_FAILURE(180031,"当前状态已发生变化，请返回上一级进行处理。"),
    SALE_DRAFT_UPDATE_FAILURE(180032,"当前状态已发生变化，请返回上一级进行处理。"),
    SALE_NETSIGNATURE_UPDATE_FAILURE(180033,"当前状态已发生变化，请返回上一级进行处理。"),
    SALE_SELL_UPDATE_FAILURE(180034,"当前状态已发生变化，请返回上一级进行处理。"),
    SUBMIT_FLOW_TASK_FAILURE(180035,"当前状态已发生变化，请返回上一级进行处理。"),

    FLOW_START_FAILURE(180110,"审批流程启动失败"),
    FLOW_CHECK_FAILURE(180111,"流程任务操作失败"),
    ;


    private Integer code;
    private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
