package com.cuttingEdge.bunan.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public record MemberNicknameCheckDto(


        @NotBlank(message = "별명은 필수 입력 값입니다.")
        @Pattern(regexp = "^[가-힣a-zA-Z]{2,10}$", message = "별명은 2~10자의 한글 또는 영어로만 구성되어야 합니다.")
        String nickname){

}