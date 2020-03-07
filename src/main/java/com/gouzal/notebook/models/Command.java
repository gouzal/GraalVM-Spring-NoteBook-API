package com.gouzal.notebook.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Command {
    String code;

    public String getInterpreter() {
        String interpreterWithPrefix = code.split(" ")[0];
        String interpreter = interpreterWithPrefix.substring(1, interpreterWithPrefix.length());
        return interpreter;

    }

    public String getScript() {
        return this.code.split(" ", 2)[1];
    }

}
