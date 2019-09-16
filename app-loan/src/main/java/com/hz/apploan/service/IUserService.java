package com.hz.apploan.service;

import com.hz.apploan.req.UserReq;
import com.hz.apploan.resp.Result;
import com.hz.apploan.service.impl.UserServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *  调用用户服务
 */
@FeignClient(value = "user-loan",fallback = UserServiceImpl.class )
public interface IUserService {

    @RequestMapping(value = "/user/reg",method = RequestMethod.POST)
    public Result userReg(@RequestBody UserReq userReq);

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public Result userLogin(@RequestBody UserReq userReq);

}
