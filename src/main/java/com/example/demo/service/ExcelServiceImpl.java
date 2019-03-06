package com.example.demo.service;

import com.example.demo.utils.ExcelUtil;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ExcelServiceImpl implements ExcelService {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Override
    public void export(List<T> datas, String fileName, String sheetName, HttpServletResponse response, boolean displayAll) throws Exception {
        try {
            ExcelUtil.exportExcel(datas,"ExcelExport","sheet",response,false);
        } catch (IOException e) {
            log.error("io异常",e);
        } catch (IllegalAccessException e) {
            log.error("非法访问异常",e);
        }
    }

    @Override
    public List<T> importExcel(MultipartFile myFile,Class<T> clazz) throws IOException {
        ExcelUtil excelUtil = new ExcelUtil(T.class);
        InputStream in = null;
        String fileName = myFile.getOriginalFilename();
        try {

            in = myFile.getInputStream();
//            in = new FileInputStream(file);
        } catch (FileNotFoundException e) {

            log.error("文件不存在",e);
        } catch (IOException e) {
            log.error("io异常",e);
        }
        List<T> list = excelUtil.importExcel(fileName,null,in);
        return list;
    }

    /**
     *@author cgq
     *@date 2018/12/21
     *@param
     *@return
     *@description excel模板下载
     */
    @Override
    public File excelDownload(){
        File file = null;
        try {
            file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "static/ExcelTemplates/ChartClassifyExcel.xlsx");
        } catch (FileNotFoundException e) {
            log.error("excel加载错误",e);
        }
        return file;
    }
}
