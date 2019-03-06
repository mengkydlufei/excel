package com.example.demo;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class J8Demo {

    String[] arrayOfWords = {"Goodbye", "World"};
    Stream<String> streamOfwords = Arrays.stream(arrayOfWords);

    List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");

    @Test
    public void de1(){

        System.out.println(streamOfwords);
        new Thread(()-> System.out.println(streamOfwords+"-----------")).start();

        new Thread(System.out::println).start();
    }

    @Test
    public void de2(){

        List<Stream<String>> list = words.stream()
                .map(word -> word.split(""))
                .map(Arrays::stream)
                .distinct()
                .collect(toList());
        System.out.println(list);


        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(toList());
        System.out.println(wordLengths);

    }

    @Test
    public void de3(){
        List<String> uniqueCharacters =
                words.stream()
                        .map(w -> w.split(""))
                        .flatMap(Arrays::stream)
                        .distinct()
                        .collect(toList());

        System.out.println(uniqueCharacters);

    }

}
