package com.gouzal.notebook.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExecutorController {

    @GetMapping(value = "/execute", produces = MediaType.APPLICATION_JSON_VALUE)
    public String execute() {
        int i=1;
        return "result:"+i;
    }

}
