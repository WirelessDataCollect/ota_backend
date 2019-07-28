package com.ruili.fota.service.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import com.ruili.fota.common.ByteUtils;
import com.ruili.fota.common.UUIDTools;
import com.ruili.fota.constant.MongoDBEnum;
import com.ruili.fota.meta.entity.FotaEntity;
import com.ruili.fota.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
     * 插入图片并获取到图片的id，需要判断固件文件的换行编码格式，windows换行'\r\n'，0x0d0a会造成后面md5值计算出错，需要转化为unix的\r和0x0a
     *
     * @param rawfile
     * @return
     */
    @Override
    public String insertFirmwareAndGetImgId(MultipartFile rawfile) throws IOException {
        //稍后填充inputStream
        FotaEntity fotaEntity = new FotaEntity(null, rawfile.getOriginalFilename(), rawfile.getSize(),
            rawfile.getContentType(), null);
        //判断上传文件的换行格式，windows换行
        ByteUtils byteUtils = new ByteUtils();
        byte[] fileContentBytes = rawfile.getBytes();
        String fileContentString = byteUtils.bytes2hexString(fileContentBytes);
        //windows格式的换行编码
        if (fileContentString.contains("0d0a")) {
            String formatFileContentString = fileContentString.replace("0d0a", "0a");
            byte[] formatFileConentBytes = byteUtils.hexStringToByte(formatFileContentString);
            InputStream formatInputStream = new ByteArrayInputStream(formatFileConentBytes);
            fotaEntity.setInputStream(formatInputStream);
        } else {
            //非windows格式的编码
            fotaEntity.setInputStream(rawfile.getInputStream());
        }

        GridFS gridFS = new GridFS(getDB(), MongoDBEnum.GridFSBucket_FIRMWARE.getGridFSBucket());
        GridFSInputFile file = gridFS.createFile(fotaEntity.getInputStream());
        //使用uuid生成全局唯一的图片id
        //        file.setChunkSize(file.getLength() > 1024 * 1024 ? 2048 * 1024 : 1024 * 1024);
        // 设定固件的ChunkSize文件大小1024K
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


    @Override
    public int deleteFirmwareByImgIds(List<String> firmwareIds) {
        GridFS gridFS = new GridFS(getDB(), MongoDBEnum.GridFSBucket_FIRMWARE.getGridFSBucket());
        for (String fId : firmwareIds) {
            gridFS.remove(fId);
        }
        return 1;
    }

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

    @Override
    public List<String> selectAllImageIds() {
        List<String> imageIdList = new ArrayList<>();
        GridFS gridFS = new GridFS(getDB(), MongoDBEnum.GridFSBucket_FIRMWARE.getGridFSBucket());
        DBCursor cursor = gridFS.getFileList();
        Iterator<DBObject> dbObjectIterator = cursor.iterator();
        while (dbObjectIterator.hasNext()) {
            imageIdList.add(dbObjectIterator.next().get("filename").toString());
        }
        return imageIdList;
    }
}
