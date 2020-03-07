package com.gouzal.notebook.models;

import com.gouzal.notebook.exceptions.InvalidScriptException;
import com.gouzal.notebook.exceptions.UnsupportedLanguageException;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.EnumUtils;

/**
 * this class describe the user input
 * and the methods to parse it
 */
@Getter
@Setter
public class Command {

    String code;

    /**
     * This method split the user script and check if it is valid and if the langague is supported,
     * after taht return the language interpreter name. otherwise rise an exception
     * @return the Interpreter that used by the User
     * @throws UnsupportedLanguageException if the Interpreter is not supported by the application
     * @throws InvalidScriptException if the script format is invalid
     * @see InvalidScriptException
     * @See UnsupportedLanguageException
     */
    public String getInterpreter() throws UnsupportedLanguageException, InvalidScriptException {
        this.getScript();
        String interpreterWithPrefix =code.split(" ")[0];
        String interpreter = interpreterWithPrefix.substring(1, interpreterWithPrefix.length());
        if(EnumUtils.isValidEnumIgnoreCase(Interpreter.class,interpreter)){
            return interpreter;
        }
        throw new UnsupportedLanguageException(interpreter);
    }

    /**
     * check whether the code send by the user is valid or not.
     * for the moment it just check if it starts with % or not.
     * @return the second part of the code that contain the script to be executed
     * @throws InvalidScriptException if the the script is invalid
     */
    public String getScript() throws InvalidScriptException {
        if(this.code.contains("%")){
            return this.code.split(" ",2)[1];
        }else{
            throw new InvalidScriptException();
        }
    }


}
