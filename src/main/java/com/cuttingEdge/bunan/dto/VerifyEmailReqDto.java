package com.cuttingEdge.bunan.dto;


import javax.validation.constraints.*;

public record VerifyEmailReqDto(

        @NotBlank(message = "이메일 필수 입력 값입니다.")
        @Email(message = "유효한 이메일 주소를 입력해주세요.")
        String email,

        @NotNull(message = "인증 번호는 필수 입력 값입니다.")
        @Digits(integer = 6, fraction = 0, message = "인증 번호는 6자리 숫자여야 합니다.")
        Integer code
){

}