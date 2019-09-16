package com.hz.loanbiz.controller;

import com.hz.loanbiz.service.ISendSmsService;
import com.hz.loanbiz.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *  短信相关controller
 */
@Controller
@RequestMapping("/sms")
public class SmsController {

    private static final Logger logger = LoggerFactory.getLogger(SmsController.class);

    @Autowired
    private ISendSmsService iSendSmsService;

    /**
     *发送短信
     * @param mobile
     * @param bizType
     * @return
     */
    @ResponseBody
    @RequestMapping("/send")
    public Result sendSms(@RequestParam("mobile") String mobile,@RequestParam("bizType")String bizType){
        return iSendSmsService.send(mobile, bizType);
    }

    /**
     *查询验证码
     * @param mobile
     * @param bizType
     * @return
     */
    @ResponseBody
    @RequestMapping("/query")
    public Result sendQuery(@RequestParam(value="mobile") String mobile, @RequestParam(value="bizType") String bizType){
        logger.info("接受到手机号：{}的查询请求:{}",mobile,bizType);
        return iSendSmsService.query(mobile, bizType);
    }

}
