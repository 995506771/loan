package com.hz.loanbiz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsCode {

    private Long id;

    private String userMobile;

    private String bizType;

    private String smsCode;

    private Date createDate;

}