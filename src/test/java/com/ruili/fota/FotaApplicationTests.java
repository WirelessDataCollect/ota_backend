package com.ruili.fota;

import com.ruili.fota.service.MongoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FotaApplicationTests {

    @Autowired
    private MongoService mongoService;

    @Test
    public void contextLoads() {
        mongoService.selectGridFS("firmware","HelloWorld.s3");
    }

}
