package com.example.demo.controller;

import com.example.demo.model.HelloWorld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private HelloWorld helloWorld;

    @GetMapping("/hello")
    public String sayHi() {
        return "Hello, " + helloWorld.getName();
    }
}
