package com.project.back.dto.request.auth;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotBlank;

import com.project.back.constant.PatternType;


@Getter
@Setter
@NoArgsConstructor
public class SignInRequestDto 
{
    @NotBlank
    @Pattern(regexp=PatternType.patternType1)
    private String password;
    @NotBlank
    @Pattern(regexp=PatternType.patternType2)
    private String userEmailId;
}
/* 분석 완료 */