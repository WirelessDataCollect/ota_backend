package com.ruili.fota.job;

import com.ruili.fota.service.FirmwareService;
import com.ruili.fota.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: liangjingxiong
 * @date: 2019-06-12
 * @description:定时任务，完成以下几个任务： 1.检查数据库中是否存在固件与MongoDB中不对应的情况，及时删除MongoDB中的无效内容（上传固件但是不上传信息的情况）
 */
@Component
public class ScheduleJob {

    @Autowired
    private MongoService mongoService;
    @Autowired
    private FirmwareService firmwareService;

    /**
     * 定时任务每天23点30分执行一次
     */
    @Scheduled(cron = "0 30 23 ? * *")
    private void task() {
        System.out.println("定时任务开始启动");
        cleanFirmwareDB();
    }

    /**
     * 清除存储桶中无效的固件内容
     */
    private void cleanFirmwareDB() {
        List<String> imageIdList = mongoService.selectAllImageIds();
        for (String imageId : imageIdList) {
            //如果固件信息为空
            if (firmwareService.selectImageByImageIdForWatcher(imageId) == null) {
                mongoService.deleteFirmwareByImgId(imageId);
                System.out.println("定时任务清除存储桶中无效的固件文件内容：" + imageId);
            }
        }
    }
}
