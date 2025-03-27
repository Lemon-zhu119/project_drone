package com.ruoyi.api.util;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.web.controller.swiper.controller.SwiperPictureController;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;

@Component
public class FileUtil {
//    private static String uploadPath;
    private final static Logger log= LoggerFactory.getLogger(FileUtil.class);

//    @Value("${file.upload.path}")
//    public void setUploadPath(String path) {
//        FileUtil.uploadPath = path;
//    }
private static final String uploadPath =  "/root/dts-server/files/";


//    private static final String filePath = "/root/dts-server/files/";
    public static boolean isExcelFile(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && (contentType.equals("application/vnd.ms-excel") ||
                contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
    }
//    public AjaxResult upload(MultipartFile file){
//        String flag;
//        String fileName = file.getOriginalFilename();
//        synchronized (SwiperPictureController.class){
//            flag = System.currentTimeMillis() + "";
//            try {
//                Thread.sleep(1L);
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
//        }
//        try {
//            if (!new File(filePath).isDirectory()){
//                new File(filePath).mkdirs(); // 使用mkdirs()创建多级目录
//            }
//            String storagePath = filePath + flag + "-" + fileName;
//            file.transferTo(new File(storagePath)); // 保存文件
//            String http = "http://116.62.160.24:8080/files/" + flag + "-" + fileName;
//            System.out.println(fileName + "--上传成功");
//            return AjaxResult.success("上传成功",http);
//        } catch (IOException e) {
//            System.err.println(fileName + "--文件上传失败");
//            e.printStackTrace();
//            return AjaxResult.error("上传失败"); // 返回错误信息
//        }
//
//    }
public static String uploadBase64File(String input) {
    try {
        String[] parts = input.split(";base64,");
        String fileType = parts[0].replace("data:image/", "");
        String imageData = parts[1];

        // 2. 使用 Commons Codec 解码
        byte[] fileBytes = Base64.decodeBase64(imageData);

        // 3. 生成文件名
        String newFileName = System.currentTimeMillis() +
                "." + fileType;

        // 4. 保存文件
        File directory = new File(uploadPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File targetFile = new File(directory, newFileName);
        FileUtils.writeByteArrayToFile(targetFile, fileBytes);

        return "http://116.62.160.24:8080/files/" + newFileName;

    } catch (Exception e) {
        log.error("文件上传失败", e);
        throw new ServiceException("文件上传失败: " + e.getMessage());
    }
}

    public static String uploadInstitution(MultipartFile file){
        try {
            log.info("当前上传路径:{}",uploadPath);
            File directory=new File(uploadPath);
            if(!directory.exists()){
                boolean created=directory.mkdirs();
                if(!created){
                    throw new ServiceException("无法创建目录");
                }
            }
            String filename=file.getOriginalFilename();

            String newFileName=System.currentTimeMillis()+"-"+filename;

            String dateDir=new SimpleDateFormat("yyyyMMdd").format(new Date());
            File targetfile=new File(directory,newFileName);
            file.transferTo(targetfile);
            String http="http://116.62.160.24:8080/files/"+newFileName;
            return http;
        }catch (IOException e){
            throw new ServiceException("文件上传失败"+e.getMessage());
        }
    }
    public static List<String> uploadFiles(List<MultipartFile> files){
        List<String> paths=new ArrayList<>();
        for(MultipartFile file:files){
            validateFile(file);
            String path=uploadInstitution(file);
            paths.add(path);
        }
        return paths;
    }
    private static void validateFile(MultipartFile file){
        if(file.getSize()>10*1024*1024){
            throw new ServiceException("文件大小不能超过10MB");
        }
        String contentType=file.getContentType();
        if(!isAllowedContentTyoe(contentType)){
            throw new ServiceException("不支持的文件类型");
        }
    }
    private static boolean isAllowedContentTyoe(String contentType){
        List<String> allowedTypes= Arrays.asList(
                "image/jpeg",
                "image/png",
                "application/pdf"
        );
        return allowedTypes.contains(contentType);
    }
}
