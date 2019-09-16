package com.hz.loanbiz.service.impl;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.hz.loanbiz.mapper.SmsCodeMapper;
import com.hz.loanbiz.model.SmsCode;
import com.hz.loanbiz.service.ISendSmsService;
import com.hz.loanbiz.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.util.Date;

@Service
public class SendSmsServiceImpl implements ISendSmsService {

    private static final Logger logger = LoggerFactory.getLogger(SendSmsServiceImpl.class);

    @Autowired
    private SmsCodeMapper smsCodeMapper;

    @Override
    public Result send(String mobile, String bizType) {
        logger.info("开始处理手机号：｛｝，发送短信请求",mobile);
        // 生成验证码
        int str = (int)((Math.random()*9+1)*100000);

        SmsSingleSender ssender = new SmsSingleSender(1400009099, "9ff91d87c2cd7cd0ea762f141975d1df37481d48700d70ac37470aefc60f9bad");
        try {
            SmsSingleSenderResult smsRlt = ssender.send(0, "86", mobile,
                    "【腾讯云】您的验证码是: 5678", "", "");

            logger.info("短信发送结果：{}",smsRlt.errMsg);
            int smsCode = smsRlt.result;
//            if(smsCode == 0){ //短信发送成功
            SmsCode userInfo = new SmsCode();
            userInfo.setBizType(bizType);
            userInfo.setCreateDate(new Date());
            userInfo.setSmsCode(String.valueOf(str));
            userInfo.setUserMobile(mobile);
            logger.info("手机号码为：｛｝",mobile);
            logger.info("请求类型：｛｝",bizType);
            smsCodeMapper.insertSelective(userInfo);
            return Result.getSuc();
//            }else{
//                return Result.getFail(-1,smsRlt.errMsg);
//            }

        } catch (HTTPException e) {
            logger.error("HTTPException",e);
            return Result.getFail(-1,"发送短信失败");
        } catch (IOException e) {
            return Result.getFail(-1,"发送短信失败");
        }

    }

    /**
     *  查询短信
     * @param mobile
     * @param bizType
     * @return
     */
    @Override
    public Result query(String mobile, String bizType) {
        logger.info("查询短信的手机号码:{}",mobile);
        SmsCode userInfo = smsCodeMapper.querySmsCodeByMobile(mobile, bizType);
        Result result = new Result();
        if(ObjectUtils.isEmpty(userInfo)){
            return Result.getFail(-1,"没有数据");
        }
        result = Result.getSuc();
        result.setT(userInfo.getSmsCode());
        return result;
    }
}
