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

    private Result setResult(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMessage();
        return this;
    }

    private Result setResult(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMessage();
        this.setData(data);
        return this;
    }

    public static Result success() {
        return new Result()
                .setResult(ResultCode.SUCCESS);
    }

    public static Result success(Object data) {
        return new Result()
                .setResult(ResultCode.SUCCESS, data);
    }

    public static Result failure(ResultCode resultCode) {
        return new Result()
                .setResult(resultCode);
    }

    public static Result failure(String message) {
        Result result = new Result();
        result.setCode(ResultCode.INTERNAL_SERVER_ERROR.getCode());
        result.setMsg(message);
        return result;
    }
}