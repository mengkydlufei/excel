package com.example.demo.service;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
@Service
public interface ExcelService {
    void export(List<T> datas, String fileName, String sheetName, HttpServletResponse response, boolean displayAll) throws Exception;

     List<T> importExcel(MultipartFile myFile,Class<T> clazz) throws IOException;

     File excelDownload() throws Exception;
}
