package com.cuttingEdge.bunan.service;

import com.cuttingEdge.bunan.config.jwt.JwtUtil;
import com.cuttingEdge.bunan.entity.Member;
import com.cuttingEdge.bunan.exception.AppException;
import com.cuttingEdge.bunan.exception.ErrorCode;
import com.cuttingEdge.bunan.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final JwtUtil jwtUtil;
    private final MemberRepository memberRepository;
    private final PasswordEncoder encoder;

    public String join(String nickname, String email, String password) { //닉네임 로직 분리해야할듯?
        // membername, email 중복 check
        if (memberRepository.existsMemberByEmail(email)){
            throw new AppException(ErrorCode.EMAIL_DUPLICATED, email + "은(는) 이미 존재합니다.");
        }

        if (memberRepository.existsMemberByNickname(nickname)){
            throw new AppException(ErrorCode.NICKNAME_DUPLICATED, nickname + "은(는) 이미 존재합니다.");
        }

        // 저장
        Member member = Member.builder()
                .nickname(nickname)
                .email(email)
                .password(encoder.encode(password))
                .build();

        memberRepository.save(member);

        return "SUCCESS";
    }

    public String login(String email, String password) {
        //membername 없음
        Member selectedMember = memberRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND, email + "이 없습니다."));

        //password 틀림
        if (!encoder.matches(password, selectedMember.getPassword())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD, "잘못된 패스워드입니다.");
        }
        log.info("로그인 성공 : {} ",selectedMember.getNickname());

        String token = jwtUtil.createToken(selectedMember.getEmail(),selectedMember.getNickname());
        // 앞에서 Exception 안났으면 토큰 발행
        log.info("토큰 발행 성공");
        
        return token;
    }

    @Transactional
    public void resetPassword(String email, String password, String newPassword) {
        //membername 확인
        Member selectedMember = memberRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND, email + "이 없습니다."));

        //password 확인
        if (!encoder.matches(password, selectedMember.getPassword())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD, "잘못된 패스워드입니다.");
        }

        selectedMember.updatePassword(encoder.encode(newPassword));
    }
    @Transactional
    public void nicknameCheck(String nickname){
        if (memberRepository.existsMemberByNickname(nickname)){
            throw new AppException(ErrorCode.NICKNAME_DUPLICATED, nickname + "은(는) 이미 존재합니다.");
        }
    }
    @Transactional
    public void emailCheck(String email){
        if (memberRepository.existsMemberByEmail(email)){
            throw new AppException(ErrorCode.EMAIL_DUPLICATED, email + "은(는) 이미 존재합니다.");
        }
    }
    public void resetDB(){
        memberRepository.deleteAll();
    }


}
