package com.cuttingEdge.bunan.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    PASSWORD_NOT_MATCHED(HttpStatus.BAD_REQUEST, "비밀번호가 확인과 일치하지 않습니다."),
    NICKNAME_DUPLICATED(HttpStatus.CONFLICT, "중복된 별명입니다."),
    EMAIL_DUPLICATED(HttpStatus.CONFLICT, "중복된 이메일입니다."),
    USERNAME_NOT_FOUND(HttpStatus.NOT_FOUND, ""),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, ""),
    PROJECT_NOT_FOUND(HttpStatus.NOT_FOUND, "프로젝트를 찾을 수 없습니다."),
    INVALID_EMAIL_CODE(HttpStatus.BAD_REQUEST, "인증번호가 일치하지 않거나 오래되었습니다."),
    MUSICID_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 음악입니다."),
    INVALID_EMAIL(HttpStatus.BAD_REQUEST, "비정상적인 이메일 요청입니다."),
    INVALID_USER(HttpStatus.BAD_REQUEST, "비정상적인 사용자 요청입니다.");

    private HttpStatus httpStatus;
    private String message;
}
