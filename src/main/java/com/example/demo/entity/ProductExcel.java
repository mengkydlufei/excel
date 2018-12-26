package com.example.demo.entity;

import com.example.demo.utils.ExcelDesc;

public class ProductExcel {

    @ExcelDesc(value = "商品编码" , orderBy = "1")
    public String  SKU;
    @ExcelDesc(value = "商品名称" , orderBy = "2")
    public String productName;
    @ExcelDesc(value = "价格" , orderBy = "3")
    public String productPrice;
}
