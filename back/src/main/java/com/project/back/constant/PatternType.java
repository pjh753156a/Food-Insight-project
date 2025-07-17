package com.project.back.constant;

public interface PatternType 
{
    String patternType1="^(?=.*[a-zA-Z])(?=.*[0-9]).{8,13}$";
    String patternType2="^[a-zA-Z0-9]*@([-.]?[a-zA-Z0-9])*\\.[a-zA-Z]{2,4}$";
}
/* 분석 완료 */