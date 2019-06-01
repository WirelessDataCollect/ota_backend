package com.ruili.fota;

import com.ruili.fota.mapper.FotaUsersMapper;
import com.ruili.fota.meta.po.FotaUsers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FotaApplicationTests {

    @Autowired
    private FotaUsersMapper fotaUsersMapper;

    @Test
    public void test() throws Exception {

        // 保存字符串
        FotaUsers users = fotaUsersMapper.selectByPrimaryKey(11);
        System.out.println(users);

    }
}
