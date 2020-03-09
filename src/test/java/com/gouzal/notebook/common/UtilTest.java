package com.gouzal.notebook.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UtilTest {
    @DisplayName("Test Filter utility method")
    @Test
    void testFilter() {
        List<String> words = Arrays.asList("2", "undefined", "hello", "world", "null", "spring", "8");
        List<String> newList = new ArrayList<>();
        for (String word : words) {
            if (word.equalsIgnoreCase("undefined") || word.equalsIgnoreCase("null")) {
                assertEquals("", Util.filter(word));
            } else {
                assertEquals(word, Util.filter(word));
            }
        }
    }

}