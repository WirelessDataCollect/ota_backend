package com.ruili.fota.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.ruili.fota.common.IdentifyCodeTools;
import com.ruili.fota.constant.SmsConst;
import com.ruili.fota.constant.result.BaseResp;
import com.ruili.fota.constant.result.ResultStatus;
import com.ruili.fota.service.RedisService;
import com.ruili.fota.service.SmsService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Yin on 2019/1/2
 */
@Service
public class SmsServiceImpl implements SmsService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 产品名称:云通信短信API产品,开发者无需替换
     */
    static final String PRODUCT = "Dysmsapi";
    /**
     * 产品域名,开发者无需替换
     */
    static final String DOMAIN = "dysmsapi.aliyuncs.com";

    @Value("${aliyun.sms.ACCESS_KEY_ID}")
    private String accessKeyId;
    @Value("${aliyun.sms.ACCESS_KEY_SECRET}")
    private String accessKeySecret;

    @Resource
    private RedisService redisService;

    /**
     * 发送短信服务
     * 要判断下这个手机号是不是当前用户绑定的
     *
     * @param phone
     * @param adminId
     * @return
     */
    @Override
    public BaseResp sendMessage(String phone, Integer adminId) {
        if (StringUtils.isEmpty(phone)) {
            return new BaseResp(ResultStatus.error_invalid_argument);
//                    ServiceResultHelper.genResultWithFaild(Constant.ErrorCode.INVALID_PARAM_MSG, Constant.ErrorCode.INVALID_PARAM_CODE);
        }

        String identifyCode;
        //1. 判断是否缓存该账号验证码
        String returnCode = redisService.getValue(phone + SmsConst.SMS_IDENTIFY_CODE);
        if (!StringUtils.isEmpty(returnCode)) {
            identifyCode = returnCode;
        } else {
            identifyCode = IdentifyCodeTools.getRandom();
        }

        //2.发送短信
        JSONObject codeMap = new JSONObject();
        codeMap.put("code", identifyCode);

        SendSmsResponse response;
        try {
            response = sendSms(phone, SmsConst.CH_SIGNATURE, SmsConst.SMS_IDENTIFY_TEMPLATE, codeMap.toString(), "yourOutId");
            //短信发送成功后存入redis
            if (response != null && SmsConst.SMS_SEND_STATUS_OK.equalsIgnoreCase(response.getCode()) && StringUtils.isEmpty(returnCode)) {
                redisService.setKey(phone + SmsConst.SMS_IDENTIFY_CODE, identifyCode);
            }
            return new BaseResp(ResultStatus.SUCCESS, response);
        } catch (Exception e) {
            logger.error("sendMessage method invoke error: {}", e.getMessage());
        }
        return new BaseResp(ResultStatus.FAIL, "短信发送失败", null);
    }

    /**
     * 发送短信接口
     *
     * @param phoneNums
     * @param signName      模板签名
     * @param templeteCode  模板代码
     * @param templateParam 模板替换参数
     * @param outId         提供给业务方扩展字段
     * @return
     * @throws ClientException
     */
    @Override
    public SendSmsResponse sendSms(String phoneNums, String signName, String templeteCode, String templateParam, String outId) throws ClientException {
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", PRODUCT, DOMAIN);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phoneNums);
        //必填:短信签名-可在短信控制台中找到
        //众评众测
        request.setSignName(signName);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templeteCode);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //{"code":"152745"}
        request.setTemplateParam(templateParam);

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        //zpzc
        request.setOutId(outId);

        //hint 此处可能会抛出异常，注意catch

        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        acsClient.getAcsResponse(request);
        return sendSmsResponse;
    }

    /**
     * 判断验证码是否正确
     *
     * @param phone
     * @param identifyCode
     * @return
     */
    @Override
    public BaseResp checkIsCorrectCode(String phone, String identifyCode) {
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(identifyCode)) {
            return new BaseResp(ResultStatus.error_invalid_argument);
        }
        String returnCode = redisService.getValue(phone + SmsConst.SMS_IDENTIFY_CODE);
        System.out.println("returnCode" + returnCode);
        if (!StringUtils.isEmpty(returnCode) && returnCode.equals(identifyCode)) {

            return new BaseResp(ResultStatus.SUCCESS);
        }
        return new BaseResp(ResultStatus.FAIL);
    }

    /**
     * 查询短信发送明细
     *
     * @param phoneNumber
     * @param bizId
     * @return
     * @throws ClientException
     */
    @Override
    public QuerySendDetailsResponse querySendDetails(String phoneNumber, String bizId) throws ClientException {
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", PRODUCT, DOMAIN);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象
        QuerySendDetailsRequest request = new QuerySendDetailsRequest();
        //必填-号码
        request.setPhoneNumber(phoneNumber);
        //可选-流水号
        request.setBizId(bizId);
        //必填-发送日期 支持30天内记录查询，格式yyyyMMdd
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
        request.setSendDate(ft.format(new Date()));
        //必填-页大小
        request.setPageSize(10L);
        //必填-当前页码从1开始计数
        request.setCurrentPage(1L);

        //hint 此处可能会抛出异常，注意catch
        QuerySendDetailsResponse querySendDetailsResponse = acsClient.getAcsResponse(request);
        return querySendDetailsResponse;
    }

}
