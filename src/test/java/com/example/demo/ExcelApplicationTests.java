package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.PrintStream;


public class ExcelApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void io(){
        File file = new File("E://jpg");

        System.out.println(file.list());
        System.out.println(file.listFiles());

    }

}
