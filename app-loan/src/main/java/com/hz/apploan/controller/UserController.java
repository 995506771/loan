package com.hz.apploan.controller;

import com.hz.apploan.req.UserReq;
import com.hz.apploan.resp.Result;
import com.hz.apploan.service.ISmsService;
import com.hz.apploan.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/user")
@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ISmsService iSmsService;

    @Autowired
    private IUserService iUserService;

    /**
     *  发送短信
     * @param userReq
     * @return
     */
    @ResponseBody
    @RequestMapping("/sms")
    public Result sendSms(UserReq userReq){
        logger.info("接收到发送短信的请求，手机号:{}",userReq.getMobile());
        // 获取手机号
        String mobile = userReq.getMobile();
        // 业务类型 什么场景要发验证码
        String bizType = userReq.getBizType();
        if(StringUtils.isEmpty(mobile)){
            return Result.getFail(-1,"mobile不能为空");
        }
        if(StringUtils.isEmpty(bizType)){
            return Result.getFail(-1,"bizType不能为空");
        }

        return iSmsService.sendSms(mobile,bizType);
    }

    /**
     *  用户注册
     * @param userReq
     * @return
     */
    @ResponseBody
    @RequestMapping("/reg")
    public Result userReg(@RequestBody UserReq userReq){
        logger.info("接受到手机号:{}注册请求...",userReq.getMobile());
        String verifyCode = userReq.getVerifyCode();
        String mobile = userReq.getMobile();
        if (StringUtils.isEmpty(verifyCode)){
            return Result.getFail(-1,"短信验证不能为空");
        }

        if (StringUtils.isEmpty(mobile)){
            return Result.getFail(-1,"手机号不能为空");
        }

        if (StringUtils.isEmpty(userReq.getPassword())){
            return Result.getFail(-1,"密码不能为空");
        }

        if (StringUtils.isEmpty(userReq.getBizType())){
            return Result.getFail(-1,"bizType不能为空");
        }

        return iUserService.userReg(userReq);
    }


    /**
     * 用户登录
     * @param userReq
     * @return
     */
    @ResponseBody
    @RequestMapping("/login")
    public Result userLogin(@RequestBody UserReq userReq){
        if (StringUtils.isEmpty(userReq.getMobile())){
            return Result.getFail(-1,"手机号不能为空");
        }

        if (StringUtils.isEmpty(userReq.getPassword())){
            return Result.getFail(-1,"密码不能为空");
        }

        return iUserService.userLogin(userReq);
    }

}
