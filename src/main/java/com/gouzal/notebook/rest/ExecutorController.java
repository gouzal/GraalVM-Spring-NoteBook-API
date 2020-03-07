package com.gouzal.notebook.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExecutorController {
    @GetMapping("/execute")
    public String execute() {
        return "hello";
    }

}
