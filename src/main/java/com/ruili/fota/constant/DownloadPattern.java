package com.ruili.fota.constant;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author: liangjingxiong
 * @date: 2019-06-11
 * @description:下载模式,读取配置文件中的配置
 */
@Component
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DownloadPattern implements Serializable {

    private static final long serialVersionUID = -4068350380975297630L;

    /**
     * 在线烧写模式，需要等待CAN总线烧写完毕
     */
    @Value("${fotaconfig.online-download-pattern}")
    public int OnlineDownloadPattern;
    /**
     * 离线烧写模式，等待传输结束后直接完结
     */
    @Value("${fotaconfig.offline-download-pattern}")
    public int OfflineDownloadPattern;
    /**
     * 每次下发的分包数
     */
    @Value("${fotaconfig.package-segmentation}")
    public int packageSegmentation;
    /**
     * 每次去数据库IO流取的字节数，下发量=eachBatch*packageSegmentation
     */
    @Value("${fotaconfig.each-batch}")
    public int eachBatch;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"OnlineDownloadPattern\":")
            .append(OnlineDownloadPattern);
        sb.append(",\"OfflineDownloadPattern\":")
            .append(OfflineDownloadPattern);
        sb.append(",\"packageSegmentation\":")
            .append(packageSegmentation);
        sb.append(",\"eachBatch\":")
            .append(eachBatch);
        sb.append('}');
        return sb.toString();
    }
}
