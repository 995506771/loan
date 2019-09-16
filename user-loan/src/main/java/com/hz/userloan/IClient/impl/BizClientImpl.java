package com.hz.userloan.IClient.impl;

import com.hz.userloan.IClient.IBizClient;
import com.hz.userloan.vo.Result;
import org.springframework.stereotype.Component;
@Component
public class BizClientImpl implements IBizClient {
    @Override
    public Result getSmsCode(String mobile, String bizType) {

        return Result.getFail(-1,"查询验证码失败");
    }
}
