package com.ruili.fota;

import com.alibaba.fastjson.JSON;
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
import java.util.Date;


@SpringBootTest
public class Test {

    public static void main(String[] args) throws InterruptedException {
//        String str = "{\"RecID\":13,\"SendID\":13,\"imei\":\"865192040010133\",\"cannum\":1,\"measure\":23,\"firmwareId\":\"3f3c0ae6c77442a4aa84800e6ae500f6\",\"mcuType\":\"单通道控制器\",\"fotaImages\":{\"gid\":1,\"firmwareId\":\"3f3c0ae6c77442a4aa84800e6ae500f6\",\"mcuType\":\"单通道控制器\",\"fileName\":\"helloworld\",\"uploader\":\"我波哥波哥\",\"uploadertel\":\"123123\",\"firmVersion\":\"1.0.0\",\"content\":\"info\",\"gmtcreate\":\"2019-06-14 19:34:37.224776\",\"gmtupdate\":\"2019-06-14 19:34:37.224776\"}}";
//        ConfigBO configBO = JSON.parseObject(str, ConfigBO.class);
//        System.out.println(configBO);

    }
}
