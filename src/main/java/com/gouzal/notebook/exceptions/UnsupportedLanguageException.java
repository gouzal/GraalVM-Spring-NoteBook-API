package com.gouzal.notebook.exceptions;

public class UnsupportedLanguageException extends Exception {
    public UnsupportedLanguageException(){
        super("The language That you are using is not Supported");
    }

    public UnsupportedLanguageException(String language){
        super("The language ["+language+"] That you are using is not Supported");
    }

}
