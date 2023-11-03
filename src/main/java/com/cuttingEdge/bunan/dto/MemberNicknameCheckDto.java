package com.cuttingEdge.bunan.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public record MemberNicknameCheckDto(
        @NotBlank(message = "별명은 필수 입력 값입니다.")
        @Pattern(regexp = "\\S+", message = "별명에 공백을 포함할 수 없습니다.")
        String nickname){

}