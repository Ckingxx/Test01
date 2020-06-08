package com.poi.imports.utils;

import org.springframework.http.HttpRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

public class UploadFile {


    public static boolean fileUpload(MultipartFile[] files, HttpServletRequest request,String path){
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                return savefile(request,file,path);
            }
        }
        return false;
    }
 public static boolean isNull(){
        return false;
 }
    /**
     * 保存文件
     * @param request
     * @param file
     * @param path
     * @return
     */
    public static boolean savefile(HttpServletRequest request, MultipartFile file,String path){
           if(!file.isEmpty()){
               File saveDir = new File(String.valueOf(file));
               if (!saveDir.getParentFile().exists()){
                   saveDir.getParentFile().mkdirs();
                   try {
                       file.transferTo(saveDir);
                       return true;
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }
           }
           return false;
    }
}
