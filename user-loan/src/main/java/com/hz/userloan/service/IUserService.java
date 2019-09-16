package com.hz.userloan.service;

import com.hz.userloan.vo.Result;
import com.hz.userloan.vo.UserLoginReq;
import com.hz.userloan.vo.UserRegReq;

public interface IUserService {

    /**
     *  用户注册
     * @param userRegReq
     * @return
     */
    public Result userRegister(UserRegReq userRegReq);

    /**
     *  用户登录
     * @param userLoginReq
     * @return
     */
    public Result userLogin(UserLoginReq userLoginReq);

}
