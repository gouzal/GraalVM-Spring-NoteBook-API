package com.gouzal.notebook.models;

import com.gouzal.notebook.exceptions.InvalidScriptException;
import com.gouzal.notebook.exceptions.UnsupportedLanguageException;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.EnumUtils;

@Getter
@Setter
public class Command {

    String code;

    public String getInterpreter() throws UnsupportedLanguageException, InvalidScriptException {
        this.getScript();
        String interpreterWithPrefix =code.split(" ")[0];
        String interpreter = interpreterWithPrefix.substring(1, interpreterWithPrefix.length());
        if(EnumUtils.isValidEnumIgnoreCase(Interpreter.class,interpreter)){
            return interpreter;
        }
        throw new UnsupportedLanguageException(interpreter);
    }
    public String getScript() throws InvalidScriptException {
        if(this.code.contains("%")){
            return this.code.split(" ",2)[1];
        }else{
            throw new InvalidScriptException();
        }
    }


}
