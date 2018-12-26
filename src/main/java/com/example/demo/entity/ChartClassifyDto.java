package com.example.demo.entity;

import com.example.demo.utils.ExcelDesc;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 *报表类型
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ChartClassifyDto extends BaseDto {
    private static final long serialVersionUID = 1L;

    /**
     * 报表类型名称
     */
    @ExcelDesc(value = "chartName" , orderBy = "1")
    private String chartName;
    /**
     * 父id
     */
    @ExcelDesc(value = "parentId" , orderBy = "2")
    private Long parentId;
    /**
     * url
     */
    @ExcelDesc(value = "url" , orderBy = "3")
    private String url;

    /**
     * 隐藏状态(1:显示  0:隐藏)
     */
    @ExcelDesc(value = "status" , orderBy = "4")
    private Integer status;
    /**
     *前端函数名,调用对应js展示数据
     */
    private String functionName;
    /**
     * 子节点
     */
    private List<ChartClassifyDto> children;


}
