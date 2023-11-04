package com.cuttingEdge.bunan.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    PASSWORD_NOT_MATCHED(HttpStatus.BAD_REQUEST, "비밀번호가 확인과 일치하지 않습니다.."),
    NICKNAME_DUPLICATED(HttpStatus.CONFLICT, "중복된 별명입니다."),
    EMAIL_DUPLICATED(HttpStatus.CONFLICT, "중복된 이메일입니다."),
    USERNAME_NOT_FOUND(HttpStatus.NOT_FOUND, ""),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, ""),
    PROJECT_NOT_FOUND(HttpStatus.NOT_FOUND, "프로젝트를 찾을 수 없습니다."),
    PROJECT_NOT_FOUND_BY_EMAIL(HttpStatus.NOT_FOUND, "해당 이메일 사용자의 프로젝트를 찾을 수 없습니다."),
    CODE_NOT_FOUND(HttpStatus.NOT_FOUND, "코드파일을 찾을 수 없습니다."),
    DIRECTORY_NOT_FOUND(HttpStatus.NOT_FOUND, "디렉토리를 찾을 수 없습니다."),
    PROJECT_NAME_DUPLICATED(HttpStatus.CONFLICT, "이미 존재하는 프로젝트 이름입니다."),
    INVALID_EMAIL_CODE(HttpStatus.BAD_REQUEST, "인증번호가 일치하지 않거나 오래되었습니다.");
    private HttpStatus httpStatus;
    private String message;
}
