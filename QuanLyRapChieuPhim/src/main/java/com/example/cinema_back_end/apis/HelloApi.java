package com.example.cinema_back_end.apis;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping(value="/api/hello", produces = "application/json")
public class HelloApi {

    @GetMapping("/")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello, World!");
    }
}
