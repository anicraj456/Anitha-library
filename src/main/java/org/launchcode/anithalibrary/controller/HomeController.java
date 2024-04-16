package org.launchcode.anithalibrary.controller;

import org.springframework.web.bind.annotation.GetMapping;



// Anitha: What does this do?
public class HomeController {
    @GetMapping("/library")
    public String index () {
        return "user/index";
    }
    @GetMapping("/")
    public String defaultHome () {
        return "user/index";
    }

    @GetMapping("")
    public String defaultHomeWithNoSlash () {
        return "user/index";
    }
}