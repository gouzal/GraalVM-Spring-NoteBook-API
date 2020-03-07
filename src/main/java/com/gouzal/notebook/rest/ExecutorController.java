package com.gouzal.notebook.rest;

import com.gouzal.notebook.exceptions.InvalidScriptException;
import com.gouzal.notebook.exceptions.UnsupportedLanguageException;
import com.gouzal.notebook.models.Command;
import com.gouzal.notebook.models.Util;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@RestController
public class ExecutorController {

    @Autowired
    HttpSession httpSession;

    @GetMapping(value = "/execute", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity execute(@RequestBody Command command) throws InvalidScriptException, UnsupportedLanguageException {
        Context context = Context.create();
        Value result = context.eval(command.getInterpreter(), command.getScript());
        Map resultResponse = new HashMap<String, String>();
        resultResponse.put("result", Util.filter(result.toString()));
        context.close();

        return new ResponseEntity<Map>(resultResponse, HttpStatus.OK);
    }


}
