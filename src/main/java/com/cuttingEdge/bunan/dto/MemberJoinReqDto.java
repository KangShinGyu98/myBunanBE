package com.cuttingEdge.bunan.dto;


import javax.validation.constraints.*;

public record MemberJoinReqDto(
        @NotBlank(message = "별명은 필수 입력 값입니다.")
        @Pattern(regexp = "^[가-힣a-zA-Z0-9]{2,12}$", message = "별명은 2~12자의 한글 또는 영어로만 구성되어야 합니다.")
        String nickname,
        @Email(message = "이메일 형식이 올바르지 않습니다.")
        String email,
        @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
        @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문, 숫자, 특수문자를 사용하세요.")
        String password,
        @NotBlank(message = "비밀번호 확인은 필수 입력 값입니다.")
        @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문, 숫자, 특수문자를 사용하세요.")
        String passwordCheck,
        @NotNull(message = "인증 번호는 필수 입력 값입니다.")
        @Digits(integer = 6, fraction = 0, message = "인증 번호는 6자리 숫자여야 합니다.")
        Integer code


) {
}