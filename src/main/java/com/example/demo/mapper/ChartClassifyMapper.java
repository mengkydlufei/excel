package com.example.demo.mapper;


import com.example.demo.entity.ChartClassifyDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ChartClassifyMapper {
    List<ChartClassifyDto> queryTree(long parentId) throws Exception;


    List<ChartClassifyDto> queryAll() throws Exception;



    int saveChartClassify(ChartClassifyDto chartClassifyDto) throws Exception;


}
