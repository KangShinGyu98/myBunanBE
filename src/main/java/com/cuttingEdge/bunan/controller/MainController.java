package com.cuttingEdge.bunan.controller;

import com.cuttingEdge.bunan.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MainController {
    private final MemberRepository memberRepository;

    @GetMapping("/")
    public ResponseEntity<String> Test(){
        return ResponseEntity.ok().body("Connected!\n");
    }

    @GetMapping("/.well-known/acme-challenge/AFYZPplL0NoUwUv9FlEJUNVI5HIqJwWdHbN87q4USSA")
    public ResponseEntity<String> Cert(){
        return ResponseEntity.ok().body("AFYZPplL0NoUwUv9FlEJUNVI5HIqJwWdHbN87q4USSA.YNLihTHoolqwnCae8NEpUqxSpem5oemNPtqwhcAZK6U");
    }
}
