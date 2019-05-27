package com.ruili.fota.common;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yin on 2018/12/27
 */
public class WXTools {
    private static String appId="wxe8219c459566d5b3";
    private static String appScret="c8592e09a747a3f2ae31a19361357ca0";
    private static String wxLoginUrl="https://api.weixin.qq.com/sns/jscode2session";

    /**
     * 返回破解出来的openID
     *
     * @param encryptedData
     * @param iv
     * @param code
     * @return 错误是返回null;
     */
    public static String doLogin(String encryptedData, String iv, String code) {
        String openidResult=null;

        //////////////// 1、向微信服务器 使用登录凭证 code 获取 session_key 和 openid
        // 请求参数
        String params = "appid=" + appId + "&secret=" + appScret + "&js_code=" + code + "&grant_type=authorization_code";
        // 发送请求
        String getInfoStr = HttpTools.sendGet(wxLoginUrl, params);
        // 解析相应内容（转换成json对象）
        JSONObject getInfo = JSONObject.parseObject(getInfoStr);
        // 获取会话密钥（session_key）
        String session_key = getInfo.get("session_key").toString();
        // 用户的唯一标识（openid）
        String openid = getInfo.get("openid").toString();
        System.out.println("这是哪一个openid?"+openid);

        //////////////// 2、对encryptedData加密数据进行AES解密 ////////////////
        try {
            String result = AesCbcTools.decrypt(encryptedData, session_key, iv, "UTF-8");
            //破解成功
            if (null != result && result.length() > 0) {

                JSONObject userInfoJSON = JSONObject.parseObject(result);
                Map userInfo = new HashMap();
                userInfo.put("openId", userInfoJSON.get("openId"));
                userInfo.put("nickName", userInfoJSON.get("nickName"));
                userInfo.put("gender", userInfoJSON.get("gender"));
                userInfo.put("city", userInfoJSON.get("city"));
                userInfo.put("province", userInfoJSON.get("province"));
                userInfo.put("country", userInfoJSON.get("country"));
                userInfo.put("avatarUrl", userInfoJSON.get("avatarUrl"));
                // 解密unionId & openId;
                if (userInfoJSON.containsKey("unionId")) {
                    userInfo.put("unionId", userInfoJSON.get("unionId"));
                }
                System.out.println(result);
                openidResult= userInfoJSON.get("openId").toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  openidResult;
    }
}
