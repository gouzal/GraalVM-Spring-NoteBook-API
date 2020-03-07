package com.gouzal.notebook.rest;

import com.gouzal.notebook.models.Command;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sun.awt.SunHints;

import javax.naming.Context;

@RestController
public class ExecutorController {

    @GetMapping(value = "/execute", produces = MediaType.APPLICATION_JSON_VALUE)
    public String execute(@RequestBody Command command) {


        return "result:"+command.getCode();
    }

}
