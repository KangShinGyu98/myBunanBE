package com.cuttingEdge.bunan.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public record MailEmailReqDto(
        @NotBlank(message = "이메일 필수 입력 값입니다.")
        @Email(message = "유효한 이메일 주소를 입력해주세요.")
        String email){

}