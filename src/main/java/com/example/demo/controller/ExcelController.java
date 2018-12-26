package com.example.demo.controller;

import com.example.demo.mapper.ChartClassifyMapper;
import com.example.demo.entity.ChartClassifyDto;
import com.example.demo.service.ExcelService;
import com.example.demo.utils.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ExcelController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ChartClassifyMapper chartClassifyMapper;

    @Autowired
    private ExcelService excelService;
    ExcelUtil excelUtil = new ExcelUtil(ChartClassifyDto.class);

    @RequestMapping("import")
    public void importExcel(MultipartFile myFile){
//        File file = new File("D:\\data\\ExcelExport.xls");
        InputStream in = null;
        String fileName = myFile.getOriginalFilename();
        if (myFile.isEmpty()) {
            log.error("文件不存在！");
        }
        try {

            in = myFile.getInputStream();
//            in = new FileInputStream(file);
        } catch (FileNotFoundException e) {

            log.error("文件不存在",e);
        } catch (IOException e) {
            log.error("io异常",e);
        }
        List<ChartClassifyDto> list = excelUtil.importExcel(fileName,null,in);
        for (ChartClassifyDto classifyDto : list) {
            try {
                chartClassifyMapper.saveChartClassify(classifyDto);
            } catch (Exception e) {
                log.error("导入数据库出现异常",e);
            }
        }
    }

    @RequestMapping("export")
    public void export(HttpServletResponse response){
        List<ChartClassifyDto> list = new ArrayList<>();
        try {
           list = chartClassifyMapper.queryAll();
        } catch (Exception e) {
            log.error("导出数据出现异常",e);
        }
        try {
            ExcelUtil.exportExcel(list,"ExcelExport","sheet",response,false);
        } catch (IOException e) {
            log.error("io异常",e);
        } catch (IllegalAccessException e) {
            log.error("非法访问异常",e);
        }
    }




}
