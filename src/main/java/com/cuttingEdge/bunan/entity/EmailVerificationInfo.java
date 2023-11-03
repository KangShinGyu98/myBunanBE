package com.cuttingEdge.bunan.entity;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class EmailVerificationInfo {
    private int verificationCode;
    private boolean verified;
    private LocalDateTime issueTime;

    public EmailVerificationInfo(int verificationCode) {
        this.verificationCode = verificationCode;
        this.verified = false;
        this.issueTime = LocalDateTime.now();
    }

    public void verify() {
        this.verified = true;
    }
    // 인증 번호 확인 메서드


    // 기타 필요한 getter/setter 추가
}
