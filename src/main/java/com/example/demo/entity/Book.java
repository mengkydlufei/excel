package com.example.demo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Book {

    private String name;
    private Integer create;
    private String node;

    public Book(String name,Integer create,String node){
        this.name = name;
        this.create = create;
        this.node = node;
    }
}
