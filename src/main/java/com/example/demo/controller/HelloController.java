package com.example.demo.controller;

import com.example.demo.HelloWorld;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    @GetMapping("/hello")
    public HelloWorld sayHi() {
        return new HelloWorld("Hello World");
    }
}
