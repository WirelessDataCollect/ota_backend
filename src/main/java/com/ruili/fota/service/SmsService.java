package com.ruili.fota.service;

import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.ruili.fota.constant.result.BaseResp;

/**
 * Created by Yin on 2019/1/2
 */
public interface SmsService {
    /**
     * 发送短信接口
     *
     * @param phoneNums     手机号码
     * @param signName      模板签名
     * @param templeteCode  模板代码
     * @param templateParam 模板替换参数
     * @param outId         提供给业务方扩展字段
     * @return
     * @throws ClientException
     */
    SendSmsResponse sendSms(String phoneNums, String signName, String templeteCode,
                            String templateParam, String outId) throws ClientException;

    /**
     * 查询短信发送明细
     *
     * @param phoneNumber
     * @param bizId       业务流水号
     * @return
     * @throws ClientException
     */
    QuerySendDetailsResponse querySendDetails(String phoneNumber, String bizId) throws ClientException;

    /**
     * 发送短信服务
     * 要判断下这个手机号是不是当前用户绑定的
     *
     * @param phone   手机号
     * @param adminId 当前用户
     * @return
     */
    BaseResp sendMessage(String phone, Integer adminId);


    /**
     * 判断验证码是否正确
     *
     * @param phone
     * @param identifyCode
     * @return
     */
    BaseResp checkIsCorrectCode(String phone, String identifyCode);

}
