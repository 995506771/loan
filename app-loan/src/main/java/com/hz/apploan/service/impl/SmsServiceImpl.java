package com.hz.apploan.service.impl;

import com.hz.apploan.resp.Result;
import com.hz.apploan.service.ISmsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *  用户相关接口 实现类
 */
@Component
public class SmsServiceImpl implements ISmsService {


    @Override
    public Result sendSms(String mobile, String bizType) {

        return Result.getFail(-1,"发送短信失败");
    }
}
