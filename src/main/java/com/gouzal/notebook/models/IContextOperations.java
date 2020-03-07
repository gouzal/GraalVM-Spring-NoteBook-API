package com.gouzal.notebook.models;


import com.gouzal.notebook.exceptions.InvalidScriptException;
import com.gouzal.notebook.exceptions.UnsupportedLanguageException;
import org.graalvm.polyglot.Context;

import java.util.Map;

public interface IContextOperations {
    public Map execute(Context context) throws InvalidScriptException, UnsupportedLanguageException;
}
