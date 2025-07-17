package com.project.back.dto.response.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.back.dto.response.ResponseCode;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.ResponseMessage;
import com.project.back.entity.UserEntity;

import lombok.Getter;

@Getter
public class GetUserInfoResponseDto extends ResponseDto 
{
    private String userRole;
    private String userEmailId;
    private String businessRegistrationNumber;
    
    private GetUserInfoResponseDto(UserEntity userEntity) 
    {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.userRole=userEntity.getUserRole();
        this.userEmailId=userEntity.getUserEmailId();
        this.businessRegistrationNumber=userEntity.getBusinessRegistrationNumber();
    }
    
    public static ResponseEntity<GetUserInfoResponseDto> success(UserEntity userEntity) 
    {
        GetUserInfoResponseDto responseBody = new GetUserInfoResponseDto(userEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
/* /분석 완료/ */