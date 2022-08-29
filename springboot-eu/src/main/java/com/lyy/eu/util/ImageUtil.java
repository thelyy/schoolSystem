package com.lyy.eu.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class ImageUtil {
    private final static String SAVE_IMAGE_PATH = "G:/daima/IJ/vue-eu/static/img/product/";

    /**
     * 返回文件后缀
     * @param file
     * @return
     */
    public static String getImagePath(MultipartFile file) {
        String fileName = file.getOriginalFilename();//获取原文件名
        int index = fileName.indexOf(".");
        return fileName.substring(index, fileName.length());
    }

    /**
     * 保存图片
     * @param mfile
     * @param file
     * @return
     */
    public static boolean saveImage(MultipartFile mfile , File file) {
        //查看文件夹是否存在，不存在则创建
        if(!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            //使用此方法保存必须要绝对路径且文件夹必须已存在,否则报错
            mfile.transferTo(file);
            return true;
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 新文件名
     * @param
     * @return
     */
    public static String getNewFileName(String suffix) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(new Date());
        return date + UUID.randomUUID() + suffix;
    }

    /**
     * 返回图片保存地址
     * @param name
     * @return
     */
    public static String getNewImagePath(String name) {
        return SAVE_IMAGE_PATH+name;
    }

}
