package com.ruili.fota.service;

import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


/**
* @author: liangjingxiong
* @date: 2019-04-09
* @description:
*/
public interface MongoService {

    String insertFirmwareAndGetImgId(MultipartFile file) throws IOException;

    int deleteFirmwareByImgId(String firmwareId);

    void deleteGridFS(String bucket, String fileName);

    GridFSDBFile selectGridFS(String bucket, String fileName);
}
