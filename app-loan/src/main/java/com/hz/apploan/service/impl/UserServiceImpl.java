package com.hz.apploan.service.impl;

import com.hz.apploan.req.UserReq;
import com.hz.apploan.resp.Result;
import com.hz.apploan.service.IUserService;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements IUserService {
    @Override
    public Result userReg(UserReq userReq) {
        return Result.getFail(-1,"注册失败");
    }

    @Override
    public Result userLogin(UserReq userReq) {
        return Result.getFail(-1,"登录失败");
    }
}
