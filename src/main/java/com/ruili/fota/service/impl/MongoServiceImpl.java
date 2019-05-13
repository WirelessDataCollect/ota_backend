package com.ruili.fota.service.impl;

import com.mongodb.DB;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import com.ruili.fota.common.utils.UUIDTools;
import com.ruili.fota.constant.MongoDBEnum;
import com.ruili.fota.dao.entity.FotaEntity;
import com.ruili.fota.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @author: liangjingxiong
 * @date: 2019-04-09
 * @description: 处理MongoDB操作的service类
 */
@Service
public class MongoServiceImpl implements MongoService {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 获取存储桶
     *
     * @return
     */
    private DB getDB() {
        return mongoTemplate.getMongoDbFactory().getLegacyDb();
    }

    /**
     * 插入图片并获取到图片的id
     *
     * @param fotaEntity
     * @return
     */
    @Override
    public String insertFirmwareAndGetImgId(FotaEntity fotaEntity) {
        GridFS gridFS = new GridFS(getDB(), MongoDBEnum.GridFSBucket_FIRMWARE.getGridFSBucket());
        GridFSInputFile file = gridFS.createFile(fotaEntity.getInputStream());
        //使用uuid生成全局唯一的图片id
        file.setChunkSize(file.getLength() > 1024 * 1024 ? 2048 * 1024 : 1024 * 1024);//设定固件的ChunkSize文件大小1024K
        file.put("realFileName", fotaEntity.getFileRealName());
        file.put("fileSize", fotaEntity.getFileSize());
        file.put("fileType", fotaEntity.getFileType());
        file.put("aliases", fotaEntity.getFileRealName());
        String fileName = UUIDTools.getUUID32();
        file.setFilename(fileName);
        file.setContentType(fotaEntity.getFileType());
        file.save();
        return fileName;
    }

    public boolean deleteFirmwareByImgId(String firmwareId){
        GridFS gridFS = new GridFS(getDB(), MongoDBEnum.GridFSBucket_FIRMWARE.getGridFSBucket());
        gridFS.remove(firmwareId);
        return true;
    };

    @Override
    public void deleteGridFS(String bucket, String fileName) {
        GridFS gridFS = new GridFS(getDB(), bucket);
        gridFS.remove(fileName);
    }

    @Override
    public GridFSDBFile selectGridFS(String bucket, String fileName) {
        GridFS gridFS = new GridFS(mongoTemplate.getMongoDbFactory().getLegacyDb(), bucket);
        GridFSDBFile gridFSFile = gridFS.findOne(fileName);
        return gridFSFile;
    }
}
