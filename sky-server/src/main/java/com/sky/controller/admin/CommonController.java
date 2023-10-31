package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/admin/common")
@Slf4j
@Api(tags = "通用接口")
public class CommonController {

    @Autowired
    private AliOssUtil aliOssUtil;

    /**
     * 文件上传接口
     */
    @PostMapping("/upload")
    @ApiOperation(value = "文件上传")
    public Result<String> uploadFile(@RequestParam("file") MultipartFile file){
        log.info("文件上传:{}",file);
        // 获取文件初始名称
        String originalFilename = file.getOriginalFilename();
        // 截取后缀
        String extention= originalFilename.substring(originalFilename.lastIndexOf("."));
        // 通过uuid生成文件名称
        String objectName = UUID.randomUUID().toString() + extention;
        try {
            String filePath = aliOssUtil.upload(file.getBytes(), objectName);
            return Result.success(filePath);
        } catch (IOException e) {
            log.info("文件上传失败:{}",e);
        }

        return null;
    }
}
