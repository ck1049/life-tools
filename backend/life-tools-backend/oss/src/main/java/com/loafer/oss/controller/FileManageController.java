package com.loafer.oss.controller;

import io.swagger.annotations.*;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@Api(tags = "文件管理")
@RestController
@RequestMapping("file")
public class FileManageController {


    @Resource(name = "fileManageServiceImpl")
    private IFileManageService service;

    @PostMapping(value = "upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "上传文件", notes = "上传文件到服务器")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "文件", required = true, dataType = "__file", paramType = "form")
    })
    public ResponseEntity<Boolean> upload(@RequestPart("file") MultipartFile file) {
        return ResponseEntity.ok(!service.upload(new MultipartFile[]{file}).isEmpty());
    }

    @PostMapping(value = "uploadFiles", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "上传多个文件", notes = "上传多个文件到服务器")
    public ResponseEntity<Boolean> uploadFiles(@RequestPart("files") MultipartFile[] files) {
        return ResponseEntity.ok(!service.upload(files).isEmpty());
    }
}
