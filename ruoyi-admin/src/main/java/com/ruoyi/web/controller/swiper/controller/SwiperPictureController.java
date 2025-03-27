package com.ruoyi.web.controller.swiper.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.web.controller.swiper.domain.SwiperPicture;
import com.ruoyi.web.controller.swiper.service.ISwiperPictureService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 轮播图管理Controller
 *
 * @author ruoyi
 * @date 2024-12-11
 */
@RestController
@RequestMapping("/swiper/picture")
public class SwiperPictureController extends BaseController
{
    @Autowired
    private ISwiperPictureService swiperPictureService;
//    private String filePath=System.getProperty("user.dir") + "/files/";
//    服务器用
    private String filePath = "/root/dts-server/files/";

    /**
     * 查询轮播图管理列表
     */
    @PreAuthorize("@ss.hasPermi('swiper:picture:list')")
    @GetMapping("/list")
    public TableDataInfo list(SwiperPicture swiperPicture)
    {
        startPage();
        List<SwiperPicture> list = swiperPictureService.selectSwiperPictureList(swiperPicture);
        return getDataTable(list);
    }

    /**
     * 导出轮播图管理列表
     */
    @PreAuthorize("@ss.hasPermi('swiper:picture:export')")
    @Log(title = "轮播图管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SwiperPicture swiperPicture)
    {
        List<SwiperPicture> list = swiperPictureService.selectSwiperPictureList(swiperPicture);
        ExcelUtil<SwiperPicture> util = new ExcelUtil<SwiperPicture>(SwiperPicture.class);
        util.exportExcel(response, list, "轮播图管理数据");
    }

    /**
     * 获取轮播图管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('swiper:picture:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(swiperPictureService.selectSwiperPictureById(id));
    }

    /**
     * 新增轮播图管理
     */
    @PreAuthorize("@ss.hasPermi('swiper:picture:add')")
    @Log(title = "轮播图管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody SwiperPicture swiperPicture)
    {
        return toAjax(swiperPictureService.insertSwiperPicture(swiperPicture));
    }

    /**
     * 修改轮播图管理
     */
    @PreAuthorize("@ss.hasPermi('swiper:picture:edit')")
    @Log(title = "轮播图管理", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody SwiperPicture swiperPicture)
    {
        return toAjax(swiperPictureService.updateSwiperPicture(swiperPicture));
    }

    /**
     * 删除轮播图管理
     */
    @PreAuthorize("@ss.hasPermi('swiper:picture:remove')")
    @Log(title = "轮播图管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(swiperPictureService.deleteSwiperPictureByIds(ids));
    }

    @PostMapping("/upload")
    public AjaxResult upload(MultipartFile file){
        String flag;
        String fileName = file.getOriginalFilename();
        synchronized (SwiperPictureController.class){
            flag = System.currentTimeMillis() + "";
            try {
                Thread.sleep(1L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        try {
            if (!new File(filePath).isDirectory()){
                new File(filePath).mkdirs(); // 使用mkdirs()创建多级目录
            }
            String storagePath = filePath + flag + "-" + fileName;
            file.transferTo(new File(storagePath)); // 保存文件
            String http = "files/" + flag + "-" + fileName;
//            String http = "http://127.0.0.1:8080/files/" + flag + "-" + fileName;
            System.out.println(fileName + "--上传成功");
            return AjaxResult.success("上传成功",http);
        } catch (IOException e) {
            System.err.println(fileName + "--文件上传失败");
            e.printStackTrace();
            return AjaxResult.error("上传失败"); // 返回错误信息
        }

    }

}
