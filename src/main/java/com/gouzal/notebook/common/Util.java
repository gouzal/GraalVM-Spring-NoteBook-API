package com.gouzal.notebook.common;


import java.util.Arrays;
import java.util.List;

/**
 * A general utility class for handling project specific tasks
 */
public class Util {

    private Util() {
    }

    /**
     * This method remove nonsense result words (eg. null and undefined)
     *
     * @param value the results that comes from the Interpreter
     * @return return the value if different than null and undefined.
     */
    public static String filter(String value) {
        List unwantedWords = Arrays.asList("null", "undefined");
        return unwantedWords.contains(value.toLowerCase()) ? "" : value;
    }
}
