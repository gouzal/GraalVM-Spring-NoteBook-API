package com.gouzal.notebook.models;


import java.util.Arrays;
import java.util.List;

public class Util {

    public static String filter(String value) {
        List unwantedWords = Arrays.asList(new String[]{"null", "undefined"});
        return unwantedWords.contains(value.toLowerCase()) ? "" : value;
    }
}
