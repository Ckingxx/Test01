package com.poi.imports.dao;

import com.poi.imports.mapper.CheckFieldInfoMapper;
import com.poi.imports.model.CheckFieldInfo;
import com.poi.imports.service.CheckFieldInfoService;
import com.poi.imports.utils.ImportExcelUtils;
import com.poi.imports.utils.IsNullUtils;
import com.poi.imports.utils.R;
import com.poi.imports.utils.UploadFile;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class CheckFieldInfoServiceImpl implements CheckFieldInfoService {
    @Autowired
    private CheckFieldInfoMapper checkFieldInfoMapper;
    public R ImportData(String publishTaskId, MultipartFile[] files, HttpServletRequest request) {
        //用于标识批量录入是否成功
        int count = 0;
        /**
         * 上传路径
         */
        String path = "/file/fileExcle" + files[0].getOriginalFilename();
        boolean status = UploadFile.fileUpload(files, request, path);
        if (!status) {
            return R.error("文件上传失败");
        }
        Workbook workbook = null;
        Sheet sheet = null;
        String[] headers = null;

        try {
            workbook = ImportExcelUtils.createWorkBook(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sheet = ImportExcelUtils.getSheet(workbook, 0);
        /**
         *
         */
        List<Object[]> oList = ImportExcelUtils.listfromSheet(sheet);
        if (!IsNullUtils.isEmpty(oList)) {
            headers = Arrays.asList(oList.get(0)).toArray(new String[0]);
            if (headers[0].replaceAll(" ", "").equals("ID")) {
                return R.error("请选择正确的表");
            }
        }
        List<CheckFieldInfo> senList = new ArrayList<CheckFieldInfo>();
        if (!IsNullUtils.isEmpty(oList.get(1))) {
            for (int i = 1; i < oList.size(); i++) {
                String[] rows = null;
                rows = Arrays.asList(oList.get(i)).toArray(new String[0]);
                R r = null;
                CheckFieldInfo checkFieldInfo = new CheckFieldInfo();
                checkFieldInfo.setFieldId(UUID.randomUUID().toString());
                checkFieldInfo.setFieldId(publishTaskId);
                checkFieldInfo.setId(Integer.parseInt(rows[0]));
                checkFieldInfo.setFieldname(rows[1]);
                  checkFieldInfo.setFieldtype(rows[2]);
                  checkFieldInfo.setLenPrecision(rows[3]);
                checkFieldInfo.setLenScala(rows[4]);
                 checkFieldInfo.setFieldformat(rows[5]);
                  checkFieldInfo.setChecknull(rows[6]);
                 checkFieldInfo.setCheckrepeat(rows[7]);
                checkFieldInfo.setCheckenum(rows[8]);
                 checkFieldInfo.setEnumvalue(rows[9]);
                 senList.add(checkFieldInfo);
            }
            if (senList.size() > 0){
                for (CheckFieldInfo c : senList) {
               count = checkFieldInfoMapper.insertData(c);
                    if (count <= 0){
                        R.error("批量录入异常");
                    }
                }
            }
        }
        return R.ok();
    }


}
