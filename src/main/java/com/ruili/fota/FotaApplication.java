package com.ruili.fota;

import com.ruili.fota.netty.NettyServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = "com.ruili.fota")
@MapperScan(basePackages = "com.ruili.fota.mapper")
@EnableCaching
@EnableScheduling
public class FotaApplication implements CommandLineRunner, DisposableBean {

    private static final Logger logger = LoggerFactory.getLogger(FotaApplication.class);

    @Autowired
    private NettyServer nettyServer;

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(FotaApplication.class);
        /**
         * 输出Banner到控制台
         */
        springApplication.setBannerMode(Banner.Mode.CONSOLE);
        springApplication.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        nettyServer.start();
        logger.info("netty启动" + args);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("netty关闭");
        logger.warn("netty关闭");
        nettyServer.stop();
    }
}
