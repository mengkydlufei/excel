package com.example.demo;

import com.example.demo.entity.Book;
import com.example.demo.entity.Pen;
import org.junit.Test;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

public class SortTest {


    static  DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    static Book book1 = new Book("三国演义", 1,"book1");
    static Book book2 = new Book("红楼梦", 100,"book2");
    static Book book3 = new Book("两开花", 200,"book3");
    static Book book4 = new Book("水浒传", 50,"book4");

    static Pen pen1 = new Pen(100,"red");
    static Pen pen2 = new Pen(250,"black");


    public static List<Book> addBookList(){
        List<Book> bookList = new ArrayList<>();
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
        bookList.add(book4);
        return bookList;
    }

    public static List<Pen> addPenList(){
        List<Pen> penList = new ArrayList<>();
        penList.add(pen1);
        penList.add(pen2);
        return penList;
    }

    List<Book> bookList = addBookList();
    List<Pen> penList = addPenList();
    List list = new ArrayList();


    @Test
    public void test1(){
        list.addAll(bookList);
        list.addAll(penList);
        System.out.println(list);
/*        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            try {
                Field field = iterator.next().getClass().getDeclaredField("create");


                System.out.println(iterator.next().getClass().getName());
          *//*      Field field = book1.getClass().getDeclaredField("create");
                Field field = pen1.getClass().getDeclaredField("create");*//*

                System.out.println(field.toString()+"---"+field.getName());
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }*/


      list.sort((a,b)->{
          try {
              Field fielda = a.getClass().getDeclaredField("create");
              fielda.setAccessible(true);
              Field fieldb = b.getClass().getDeclaredField("create");
              fieldb.setAccessible(true);
              Integer i = (Integer) fielda.get(a);
              System.out.println(i);

              Integer j = (Integer) fieldb.get(b);
             /* return a.getClass().getDeclaredField("create").toString().compareTo(b.getClass().getDeclaredField("create").toString());*/
            return i-j;
          } catch (NoSuchFieldException e) {
              e.printStackTrace();
          } catch (IllegalAccessException e) {
              e.printStackTrace();
          }
          return 0;
      });

        System.out.println(list);

    }


    @Test
    public void mapTest(){
        TreeMap<Integer,Object> map = new TreeMap<>();
        Iterator<Book> iterator = bookList.iterator();
        while (iterator.hasNext()){
            Book book = iterator.next();
            map.put(book.getCreate(),book);
        }
        Iterator<Pen> penIterator = penList.iterator();
        while (penIterator.hasNext()){
            Pen pen = penIterator.next();
            map.put(pen.getCreate(),pen);
        }

        System.out.println(map);
    }
}
