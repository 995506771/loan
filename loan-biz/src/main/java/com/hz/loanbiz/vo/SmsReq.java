package com.hz.loanbiz.vo;

import lombok.Data;

/**
 *  短信请求的实体
 */
@Data
public class SmsReq {

    private String mobile;

    private String bizType;

}
