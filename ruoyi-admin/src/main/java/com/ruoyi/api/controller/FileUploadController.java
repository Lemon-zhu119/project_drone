package com.ruoyi.api.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.web.controller.swiper.controller.SwiperPictureController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 轮播图管理Controller
 *
 * @author ruoyi
 * @date 2024-12-11
 */
@RestController
@RequestMapping("/api/file")
public class FileUploadController {

//        private String filePath=System.getProperty("user.dir") + "/files/";
    //    服务器用
    private String filePath = "/root/dts-server/files/";

    @PostMapping("/upload")
    public AjaxResult upload(MultipartFile file) {
        String flag;
        String fileName = file.getOriginalFilename();
        synchronized (SwiperPictureController.class) {
            flag = System.currentTimeMillis() + "";
            try {
                Thread.sleep(1L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        try {
            if (!new File(filePath).isDirectory()) {
                new File(filePath).mkdirs(); // 使用mkdirs()创建多级目录
            }
            String storagePath = filePath + flag + "-" + fileName;
            file.transferTo(new File(storagePath)); // 保存文件
            String http = "http://116.62.160.24:8080/files/" + flag + "-" + fileName;
//            String http = "http://localhost:8080/files/" + flag + "-" + fileName;
            System.out.println(http + "--上传成功");
            return AjaxResult.success("上传成功", http);
        } catch (IOException e) {
            System.err.println(fileName + "--文件上传失败");
            e.printStackTrace();
            return AjaxResult.error("上传失败"); // 返回错误信息
        }

    }

}
