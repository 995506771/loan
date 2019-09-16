package com.hz.userloan.controller;

import com.hz.userloan.service.IUserService;
import com.hz.userloan.vo.Result;
import com.hz.userloan.vo.UserLoginReq;
import com.hz.userloan.vo.UserRegReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户相关 controller
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService iUserService;

    /**
     *  注册用户
     * @param userRegReq
     * @return
     */
    @RequestMapping("/reg")
    public Result userReg(@RequestBody UserRegReq userRegReq){
        logger.info("接收到注册用户的请求，手机号:{}，验证码:{}",userRegReq.getMobile(),userRegReq.getVerifyCode());
        Result result = iUserService.userRegister(userRegReq);
        return result;
    }

    @RequestMapping("/login")
    public Result userLogin(@RequestBody UserLoginReq userLoginReq){
        logger.info("接收到登录用户的请求。。。。。。。。");
        return iUserService.userLogin(userLoginReq);
    }

}
