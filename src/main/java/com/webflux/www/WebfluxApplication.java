package com.webflux.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class WebfluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxApplication.class, args);
        Integer a = 10;
        Integer b = a;
        a = 20;
        System.out.println("a : " + a);
        System.out.println("b : " + b);
        System.out.println(a==b);

    }

}
