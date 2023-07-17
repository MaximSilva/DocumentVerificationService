package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SpringBootApplication
public class DemoApplication {
    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/second-page")
    public String secondPage() {
        return "second-page.html";
    }

    @GetMapping("/third-page")
    public String thirdPage() {
        return "third-page.html";
    }
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
