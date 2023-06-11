package com.SHOP.TaTa.controller;

import com.SHOP.TaTa.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;


@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {
    @Value("${TaTa.path}")
    private String basepath;
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file){
        String originalFilename = file.getOriginalFilename();
        String suffis = originalFilename.substring
                (originalFilename.lastIndexOf("."));

        String s = UUID.randomUUID().toString();
        String filename = s + suffis;
        log.info(file.toString());
        File fou = new File(basepath);
        if(!fou.exists()){
            fou.mkdirs();
        }
        try {
            file.transferTo(new File(basepath+filename));
        }catch (IOException e){
            e.printStackTrace();
        }
        return R.success(filename);

    }
    //文件下载
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response){

        try {
            FileInputStream fileInputStream = new FileInputStream(new File(basepath+name));
            ServletOutputStream outputStream = response.getOutputStream();
            response.setContentType("image/jpeg");
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = fileInputStream.read(bytes)) != -1){
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }
            outputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
