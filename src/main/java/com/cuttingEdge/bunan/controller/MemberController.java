package com.cuttingEdge.bunan.controller;


import com.cuttingEdge.bunan.dto.MemberJoinRequestDto;
import com.cuttingEdge.bunan.dto.MemberLoginRequestDto;
import com.cuttingEdge.bunan.dto.MemberResetRequestDto;
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

    @PostMapping("/signUp")
    public ResponseEntity<String> join(@RequestBody @Valid MemberJoinRequestDto dto) {
        memberService.join(dto.nickname(), dto.email(), dto.password());
        return ResponseEntity.ok().body("회원가입이 완료되었습니다.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid MemberLoginRequestDto dto) {
        String token = memberService.login(dto.email(), dto.password());
        return ResponseEntity.ok().body(token);
    }

//    @PostMapping("/resetPassword")
//    public ResponseEntity<String> reset(@RequestBody @Valid MemberResetRequestDto dto) {
//        memberService.resetPassword(dto.email(), dto.password(), dto.newPassword());
//        return ResponseEntity.ok().body("비밀번호가 변경되었습니다.");
//    }

    @GetMapping("/resetDB")
    public ResponseEntity<String> reset() {

        return ResponseEntity.ok().body("DB 리셋이 완료되었습니다.");
    }


}
