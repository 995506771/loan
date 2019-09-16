package com.hz.loanbiz.vo;

import lombok.Data;

/**
 *  返回给app的实体
 * @param <T>
 */
@Data
public class Result<T> {

    private String msg;

    private int code;

    private T t;

    /**
     * 返回失败
     * @param code
     * @param msg
     * @return
     */
    public static Result getFail(int code ,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    /**
     * 返回成功
     * @return
     */
    public static Result getSuc(){
        Result result = new Result();
        result.setCode(0);
        result.setMsg("请求成功");
        return result;
    }

}
