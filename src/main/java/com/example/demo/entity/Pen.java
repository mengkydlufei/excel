package com.example.demo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Pen {

    private Integer create;
    private String color;
    public Pen(Integer create,String color){
        this.create = create;
        this.color = color;
    }
}
