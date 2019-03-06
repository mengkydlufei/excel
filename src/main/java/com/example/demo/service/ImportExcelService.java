package com.example.demo.service;

import com.example.demo.utils.ImportExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ImportExcelService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public Map<String, Object> importExcel(MultipartFile multipartFile, Object obj) {
        Map<String, Object> map = new HashMap<>();
        InputStream in = null;
        List<Map<String, Object>> list = null;
        ImportExcelUtil importExcelUtil = new ImportExcelUtil();
        if (multipartFile.isEmpty()) {
            logger.error("文件不存在");
        }
        String fileName = multipartFile.getOriginalFilename();
        try {

            in = multipartFile.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            list = importExcelUtil.getBankListByExcel(in, fileName, obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(Map<String,Object> data:list){

        }

        return null;
    }
}
