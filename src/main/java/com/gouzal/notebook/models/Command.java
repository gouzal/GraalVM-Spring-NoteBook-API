package com.gouzal.notebook.models;

import com.gouzal.notebook.common.Util;
import com.gouzal.notebook.exceptions.InvalidScriptException;
import com.gouzal.notebook.exceptions.UnsupportedLanguageException;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.EnumUtils;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

import java.util.HashMap;
import java.util.Map;


/**
 * this class describe the user input
 * and the methods to parse it
 */
@Getter
@Setter
public class Command implements IContextOperations {
    private String sessionId;
    private String code;

    /**
     * This method split the user script and check if it is valid and if the langague is supported,
     * after taht return the language interpreter name. otherwise rise an exception
     * @return the Interpreter that used by the User
     * @throws UnsupportedLanguageException if the Interpreter is not supported by the application
     * @throws InvalidScriptException if the script format is invalid
     * @see InvalidScriptException
     * @See UnsupportedLanguageException
     */
    private String getInterpreter() throws UnsupportedLanguageException, InvalidScriptException {
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
    private String getScript() throws InvalidScriptException {
        if(this.code.contains("%")){
            return this.code.split(" ",2)[1];
        }else{
            throw new InvalidScriptException();
        }
    }

    /**
     * This method run the command in the given polyglot context
     * @param context the GraalVM polyglot context
     * @return the output of code execution
     * @throws InvalidScriptException
     * @throws UnsupportedLanguageException
     */
    @Override
    public Map<String,String> execute(Context context) throws InvalidScriptException, UnsupportedLanguageException {
        Value result = context.eval(this.getInterpreter(), this.getScript());
        Map resultResponse = new HashMap<String, String>();
        resultResponse.put("result", Util.filter(result.toString()));
        return resultResponse;
    }
}
