package com.ruili.fota;

import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class Test {

    public static void main(String[] args) {
        String raw = "hahahah0d0a";
        System.out.println(raw.replace("0d0a","0a"));
    }
}
