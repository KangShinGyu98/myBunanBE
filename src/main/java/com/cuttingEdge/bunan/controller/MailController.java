package com.cuttingEdge.bunan.controller;


import com.cuttingEdge.bunan.dto.MailEmailReqDto;
import com.cuttingEdge.bunan.dto.MemberNicknameCheckDto;
import com.cuttingEdge.bunan.dto.VerifyEmailReqDto;
import com.cuttingEdge.bunan.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mail")
public class MailController {

    private final MailService mailService;

    @ResponseBody
    @PostMapping("/email")
    public ResponseEntity<String> mailSend(@RequestBody @Valid MailEmailReqDto dto){

        mailService.sendMail(dto.email());
        return ResponseEntity.ok().body("SUCCESS");
    }



}
//출처 : https://velog.io/@kjh950330/%ED%9A%8C%EC%9B%90%EA%B0%80%EC%9E%85-%EC%9D%B4%EB%A9%94%EC%9D%BC-%EC%9D%B8%EC%A6%9D-%EA%B8%B0%EB%8A%A5-%EA%B5%AC%ED%98%84