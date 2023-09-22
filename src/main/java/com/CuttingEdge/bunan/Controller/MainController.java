package com.CuttingEdge.bunan.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public ResponseEntity<String> Test(){

        return ResponseEntity.ok().body("Connected!\n");
    }

}
