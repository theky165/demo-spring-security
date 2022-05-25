package com.example.demospringsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/")
public class TemplateController {

    @GetMapping(value = "login")
    public String getLogin() {
        return "login";
    }

    @GetMapping(value = "course")
    public String getCourse() {
        return "course";
    }
}
