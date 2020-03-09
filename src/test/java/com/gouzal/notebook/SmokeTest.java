package com.gouzal.notebook;

import static org.assertj.core.api.Assertions.assertThat;

import com.gouzal.notebook.restapi.ExecutorController;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmokeTest {

    @Autowired
    private ExecutorController controller;

    @Test
    public void contexLoads() throws Exception {
        //assertThat(controller).isNotNull();
    }
}
