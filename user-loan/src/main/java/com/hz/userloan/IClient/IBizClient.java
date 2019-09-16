package com.hz.userloan.IClient;

import com.hz.userloan.IClient.impl.BizClientImpl;
import com.hz.userloan.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *  调用 loan-biz 服务  调用那边接口
 */
@FeignClient(value = "loan-biz",fallback = BizClientImpl.class)
public interface IBizClient {

    /**
     *  查询 验证码
     * @param mobile
     * @param bizType
     * @return
     */
    @RequestMapping(value = "/sms/query",method = RequestMethod.POST)
    public Result getSmsCode(@RequestParam("mobile") String mobile, @RequestParam("bizType")String bizType);

}
