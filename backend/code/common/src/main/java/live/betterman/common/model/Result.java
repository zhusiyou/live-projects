package live.betterman.common.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: zhudawei
 * @date: 2023/12/27
 * @description: 统一API响应结果格式封装
 */
@Data
public class Result<T> implements Serializable {
    private Integer code;
    private String msg;
    private T data;
    private long timestamp;

    public Result() {
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMsg(ResultCode.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }

    public static Result<Void> failure(ResultCode resultCode) {
        Result<Void> result = new Result<>();
        result.setCode(resultCode.getCode());
        result.setMsg(result.getMsg());
        return result;
    }

    public static Result<Void> error(String message) {
        Result<Void> result = new Result<>();
        result.setCode(ResultCode.INTERNAL_SERVER_ERROR.getCode());
        result.setMsg(message);
        return result;
    }
}