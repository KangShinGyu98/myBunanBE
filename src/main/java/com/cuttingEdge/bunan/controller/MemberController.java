package com.cuttingEdge.bunan.controller;


import com.cuttingEdge.bunan.dto.MemberJoinReqDto;
import com.cuttingEdge.bunan.dto.MemberLoginReqDto;
import com.cuttingEdge.bunan.dto.MemberNicknameCheckDto;
import com.cuttingEdge.bunan.dto.VerifyEmailReqDto;
import com.cuttingEdge.bunan.service.MailService;
import com.cuttingEdge.bunan.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final MailService mailService;



    @PostMapping("/signUp")
    public ResponseEntity<String> join(@RequestBody @Valid MemberJoinReqDto dto) {

        mailService.verifyEmail(dto.email(), dto.code());
        memberService.join(dto.nickname(), dto.email(), dto.password(),dto.passwordCheck());
        String token = memberService.login(dto.email(), dto.password());
        return ResponseEntity.ok().body(token);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid MemberLoginReqDto dto) {
        String token = memberService.login(dto.email(), dto.password());
        return ResponseEntity.ok().body(token);
    }

//    @PostMapping("/resetPassword")
//    public ResponseEntity<String> reset(@RequestBody @Valid MemberResetRequestDto dto) {
//        memberService.resetPassword(dto.email(), dto.password(), dto.newPassword());
//        return ResponseEntity.ok().body("비밀번호가 변경되었습니다.");
//    }
    @PostMapping("/nicknameCheck")
    public ResponseEntity<String> nicknameCheck(@RequestBody @Valid MemberNicknameCheckDto dto) {
        memberService.nicknameCheck(dto.nickname());
        return ResponseEntity.ok().body( dto.nickname()+" 은 사용 가능한 닉네임 입니다.");
    }
//    @GetMapping("/resetDB")
//    public ResponseEntity<String> reset() {
//
//        return ResponseEntity.ok().body("DB 리셋이 완료되었습니다.");
//    }


}
