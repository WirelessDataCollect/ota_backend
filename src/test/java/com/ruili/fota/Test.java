package com.ruili.fota;

import com.ruili.fota.constant.LoadStatusEnum;
import com.ruili.fota.dao.entity.FotaProcessEntity;
import com.ruili.fota.netty.FotaProcessMap;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class Test {

    public static void main(String[] args) {
        String raw = "hahahah0d0a";
        System.out.println(raw.replace("0d0a","0a"));
    }
}
