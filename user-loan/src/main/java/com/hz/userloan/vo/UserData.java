package com.hz.userloan.vo;

import lombok.Data;

/**
 *  登录成功后 返回给移动端
 */
@Data
public class UserData {

    private String userId;

    private String token;

}
