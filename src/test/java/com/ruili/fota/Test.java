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
        String str = "{\"RecID\":23,\"SendID\":45,\"imei\":\"865192040010133\",\"cannum\":1,\"measure\":66,\"firmwareId\":\"191be9d1b1ac4ecf97b55f1b70a0228d\",\"mcuType\":\"中央控制器\",\"fotaImages\":{\"gid\":24,\"firmwareId\":\"191be9d1b1ac4ecf97b55f1b70a0228d\",\"mcuType\":\"单通道控制器\",\"fileName\":\"led_blink\",\"uploader\":\"我波哥波哥\",\"uploadertel\":\"123123\",\"firmVersion\":\"1.0.0\",\"content\":\"小文件50k\",\"gmtcreate\":\"Sun Jun 16 17:10:17 CST 2019\",\"gmtupdate\":\"Sun Jun 16 17:10:17 CST 2019\"}}";
        ConfigBO configBO = JSON.parseObject(str, ConfigBO.class);
        configBO.setFotaImages(null);
        System.out.println(configBO.getFotaImages() == null);

    }
}
