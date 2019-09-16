package com.hz.userloan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private Long id;

    private String userId;

    private String status;

    private String userName;

    private String userPassword;

    private String userMobile;

    private Date createDate;

    private String realName;

    private String idCard;


}