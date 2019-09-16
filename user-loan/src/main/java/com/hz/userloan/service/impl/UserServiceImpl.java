package com.hz.userloan.service.impl;

import com.hz.userloan.IClient.IBizClient;
import com.hz.userloan.mapper.UserInfoMapper;
import com.hz.userloan.model.UserInfo;
import com.hz.userloan.service.IUserService;
import com.hz.userloan.utils.JWTUtils;
import com.hz.userloan.vo.Result;
import com.hz.userloan.vo.UserData;
import com.hz.userloan.vo.UserLoginReq;
import com.hz.userloan.vo.UserRegReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private IBizClient iBizClient;

    @Override
    public Result userRegister(UserRegReq userRegReq) {
        String mobile = userRegReq.getMobile();
        String verifyCode = userRegReq.getVerifyCode();

        //注册判断验证码
        Result smsCode = iBizClient.getSmsCode(mobile,userRegReq.getBizType());
        if(ObjectUtils.isEmpty(smsCode)){
            return Result.getFail(-1,"查询数据为空");
        }
        if(smsCode.getCode()!=0){
            return Result.getFail(-1,"查询验证码失败");
        }
        logger.info("接收到了返回的smsCode数据",verifyCode);
        // 获取到的验证码
        String dbCode = (String) smsCode.getT();
        logger.info("手机号:{}，对应的数据中的验证码:{}",mobile,dbCode);
        if(!verifyCode.equals(dbCode)){
            return Result.getFail(-1,"输入的验证码不一样");
        }
        String userId = UUID.randomUUID().toString().replace("-", "");

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        userInfo.setStatus("0");
        userInfo.setCreateDate(new Date());
        userInfo.setUserMobile(mobile);
        userInfo.setUserPassword(userRegReq.getPassword());
        userInfoMapper.insertSelective(userInfo);

        return Result.getSuc();
    }

    @Override
    public Result userLogin(UserLoginReq userLoginReq) {
        logger.info("接收到登录的请求,输入的密码是:{}",userLoginReq.getPassword());
        logger.info("接收到登录的请求,输入的手机号是:{}",userLoginReq.getMobile());
        UserInfo userInfo = userInfoMapper.queryUserByMobile(userLoginReq.getMobile());
        if(ObjectUtils.isEmpty(userInfo)){
            return Result.getFail(-1,"请输入手机号");
        }
        logger.info("登录时的密码:{}",userLoginReq.getPassword());
        logger.info("数据库查出来的密码:{}",userInfo.getUserPassword());
        if(!userLoginReq.getPassword().equals(userInfo.getUserPassword())){
            return Result.getFail(-1,"密码输入错误");
        }
        // 登录成功后 保存token
        String token = JWTUtils.sign(userLoginReq.getMobile(), userLoginReq.getPassword());
        UserData userData = new UserData();
        userData.setToken(token);
        userData.setUserId(userInfo.getUserId());
        Result result = new Result();
        result.setT(userData);
        result.setCode(0);
        result.setMsg("登录成功！！");
        return result;
    }
}
