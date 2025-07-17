package com.project.back.service;

import org.springframework.http.ResponseEntity;

import com.project.back.dto.request.user.DeleteUserRequestDto;
import com.project.back.dto.request.user.PatchUserInfoRequestDto;

import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.user.GetMyInfoResponseDto;
import com.project.back.dto.response.user.GetUserInfoResponseDto;

public interface UserService 
{
  ResponseEntity<? super GetUserInfoResponseDto> GetSignInUser(String userEmailId);
  ResponseEntity<? super GetMyInfoResponseDto> getMyInfo (String userEmailId);

  ResponseEntity<ResponseDto> patchUserInfo(PatchUserInfoRequestDto dto, String userEmailId);
  ResponseEntity<ResponseDto> deleteUser(DeleteUserRequestDto dto, String userEmailId);
}
/* /분석 완료/ */