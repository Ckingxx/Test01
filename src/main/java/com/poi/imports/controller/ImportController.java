package com.poi.imports.controller;

import com.poi.imports.service.CheckFieldInfoService;
import com.poi.imports.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


@Controller
public class ImportController {
   @Autowired
    private CheckFieldInfoService checkFieldInfoService;
    @ResponseBody
    @RequestMapping("/importdata")
    public R importdata(@RequestParam("publishTaskId") String publishTaskId, @RequestParam("SensitiveExcle")
                        MultipartFile[] files, HttpServletRequest request){
            return checkFieldInfoService.ImportData(publishTaskId,files,request);
   }
}
