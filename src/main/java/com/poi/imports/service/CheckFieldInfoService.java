package com.poi.imports.service;

import com.poi.imports.utils.R;
import org.springframework.http.HttpRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface CheckFieldInfoService {
    R ImportData(String publishTaskId, MultipartFile[] files, HttpServletRequest request);
}
