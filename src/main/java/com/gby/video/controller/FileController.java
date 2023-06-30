package com.gby.video.controller;

import com.gby.video.bean.ResultVO;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;


@RestController
@RequestMapping("/file")
@CrossOrigin
public class FileController {

    @Value("${file.upload_path}")
    private String uploadPath;

    @Value("${file.static_access_path}")
    private String staticAccessPath;

    @Value("${qiniuyun.access_key}")
    private String accessKey;

    @Value("${qiniuyun.secret_key}")
    private String secretKey;

    @Value("${qiniuyun.bucket}")
    private String bucket;


    @PostMapping("/upload")
    public ResultVO upload(MultipartFile file) {
        Configuration cfg = new Configuration(Region.region1());
        UploadManager uploadManager = new UploadManager(cfg);
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        //获取原文件名
        String fileName = file.getOriginalFilename();
        //                                         .XXX   .png
        String suffix = fileName.substring(fileName.lastIndexOf("."));
//        System.out.println(fileName);
        Instant instant = Instant.now();

        String newName = instant.toEpochMilli() + suffix;

        try {
            Response response = uploadManager.put(file.getInputStream(), newName, upToken, null, null);
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return ResultVO.success("上传成功").setData(putRet);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
                return ResultVO.error("上传失败");
            } catch (QiniuException ex2) {
                return ResultVO.error("上传失败");
                //ignore
            }
        } catch (IOException e) {
            return ResultVO.error("上传失败");
        }
    }
}