package com.hz.userloan.vo;

import lombok.Data;

/**
 *  注册请求
 */
@Data
public class UserRegReq {

    private String mobile;
    private String password;
    private String verifyCode;
    private String bizType;
}
