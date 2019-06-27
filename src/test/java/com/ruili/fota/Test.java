package com.ruili.fota;

import com.alibaba.fastjson.JSON;

import com.google.common.collect.Maps;
import com.ruili.fota.common.CopyTools;
import com.ruili.fota.common.DateTools;
import com.ruili.fota.meta.bo.ConfigBO;
import com.ruili.fota.meta.bo.LoadProcessBO;
import com.ruili.fota.meta.entity.FotaProcessEntity;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SpringBootTest
public class Test {

    public static void main() throws Exception {
        String uri = "111"; // 参照文档中, 样例 /access/spot/saveSpotPriceStrategy
        String key = "11";  // 找 @景鸿 要
        String secret = "11"; // 找 @景鸿 要
        String timeStamp = String.valueOf(System.currentTimeMillis() + 50000L);

        Map<String, String> param = Maps.newHashMap();
        param.put("timestamp", timeStamp);
        param.put("key", key);
        String sign =  generateSign(param, secret, uri);
        param.put("sign", sign);
        System.out.println(JSON.toJSONString(param));
    }

    public static String generateSign(Map<String, String> param, String secret, String uri) {
        Set<String> keySet = param.keySet();
        List<String> keys = new ArrayList<>(keySet);
        keys.remove("sign");
        Collections.sort(keys);

        StringBuilder builder = new StringBuilder();
        builder.append(uri);
        for (String paramKey : keys) {
            builder.append(paramKey).append(param.get(paramKey));
        }
        builder.append(secret);
        String content = builder.toString();

        // 验证签名
        // try {
        //     return SignatureAlgorithm.MD5.sign(content, SecurityUtils.hexEncoder);
        // } catch(Exception e) {
        //     System.out.println(e);
        // }
        return null;
    }
}
