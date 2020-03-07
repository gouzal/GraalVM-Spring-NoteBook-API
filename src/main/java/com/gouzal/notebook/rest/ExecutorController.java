package com.gouzal.notebook.rest;

import com.gouzal.notebook.models.Command;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ExecutorController {

    @GetMapping(value = "/execute", produces = MediaType.APPLICATION_JSON_VALUE)
    public String execute(@RequestBody Command command) {
        Context context = Context.create();
        Value result = context.eval(command.getInterpreter(), command.getScript());
        context.close();

        return "result:" + result.asInt();
    }

}
