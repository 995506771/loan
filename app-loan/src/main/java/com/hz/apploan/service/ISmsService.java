package com.hz.apploan.service;

import com.hz.apploan.resp.Result;
import com.hz.apploan.service.impl.SmsServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *  用户相关接口
 */
@FeignClient(value = "loan-biz",fallback = SmsServiceImpl.class)
public interface ISmsService {

    /**
     *  发送短信
     * @param mobile
     * @param bizType
     * @return
     */
    @RequestMapping(value = "/sms/send", method = RequestMethod.POST)
    public Result sendSms(@RequestParam("mobile") String mobile, @RequestParam("bizType") String bizType);

}
