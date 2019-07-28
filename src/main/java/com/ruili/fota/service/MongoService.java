package com.ruili.fota.service;

import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


/**
* @author: liangjingxiong
* @date: 2019-04-09
* @description:
*/
public interface MongoService {

    /**
     * 存储固件文件并返回生成的唯一id
     * @param file
     * @return
     * @throws IOException
     */
    String insertFirmwareAndGetImgId(MultipartFile file) throws IOException;

    /**
     * 通过固件唯一id删除固件文件，批量遍历删除，后面需要改造
     * @param firmwareIds
     * @return
     */
    int deleteFirmwareByImgIds(List<String> firmwareIds);

    /**
     * 删除特定存储桶的特定文件
     * @param bucket
     * @param fileName
     */
    void deleteGridFS(String bucket, String fileName);

    /**
     * 获得特定存储桶的特定文件
     * @param bucket
     * @param fileName
     * @return
     */
    GridFSDBFile selectGridFS(String bucket, String fileName);

    /**
     * 查询所有的imageId
     * @return
     */
    List<String> selectAllImageIds();
}
