package com.project.back.dto.request.auth;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class TelNumberAuthRequestDto 
{
    @NotBlank
    private String userTelNumber;
}
/* 분석 완료 */
