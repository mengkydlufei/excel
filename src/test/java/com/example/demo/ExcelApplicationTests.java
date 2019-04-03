package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.concurrent.CountDownLatch;


public class ExcelApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void io() throws Exception{
        File file = new File("D:\\data\\a.txt");


        System.out.println(file.list());
        System.out.println(file.listFiles());


        InputStream inputStream = new FileInputStream(file);
        OutputStream outputStream = new FileOutputStream(new File("D:\\data\\b.txt"));
        byte[] bytes = new byte[10];

        while (inputStream.read(bytes) != -1){
            outputStream.write(bytes);
        }
        inputStream.close();
        outputStream.close();

    }


    @Test
    public void io2() throws Exception{
        File file = new File("D:\\data\\a.txt");


        BufferedReader fileReader = new BufferedReader(new FileReader(file));
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter("D:\\data\\b.txt"));


        String line;
        while ((line = fileReader.readLine()) != null){

            fileWriter.write(line+"/r/n");
        }
        fileReader.close();
        fileWriter.close();

    }
    @Test
    public void treads(){

    }





}
