package com.loafer.oss.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@Tag(name = "文件管理")
@RestController
@RequestMapping("file")
public class FileManageController {


    /*@Resource(name = "fileManageServiceImpl")
    private IFileManageService service;

    @PostMapping(value = "upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Parameter(description = "上传文件")
    @Parameters({
            @Parameter(name = "file", description = "文件", required = true, dataType = "__file", paramType = "form")
    })
    public ResponseEntity<Boolean> upload(@RequestPart("file") MultipartFile file) {
        return ResponseEntity.ok(!service.upload(new MultipartFile[]{file}).isEmpty());
    }

    @PostMapping(value = "uploadFiles", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Parameter(description = "上传多个文件")
    public ResponseEntity<Boolean> uploadFiles(@RequestPart("files") MultipartFile[] files) {
        return ResponseEntity.ok(!service.upload(files).isEmpty());
    }*/
}
