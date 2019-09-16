package com.hz.apploan.resp;

import lombok.Data;

@Data
public class Result {

    private String msg; // 错误 或者成功信息

    private int code;   //0-请求成功，1-请求失败


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

}
