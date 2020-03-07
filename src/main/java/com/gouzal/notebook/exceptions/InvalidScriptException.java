package com.gouzal.notebook.exceptions;

public class InvalidScriptException extends Exception {
    public InvalidScriptException(){
        super("This script is not valid");
    }
}
