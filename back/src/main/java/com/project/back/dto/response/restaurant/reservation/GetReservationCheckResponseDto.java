package com.project.back.dto.response.restaurant.reservation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.back.dto.response.ResponseDto;
import com.project.back.entity.ReservationEntity;
import com.project.back.dto.response.ResponseCode;
import com.project.back.dto.response.ResponseMessage;

import lombok.Getter;

@Getter
public class GetReservationCheckResponseDto extends ResponseDto 
{
    private String reservationUserId; 
    private Integer reservationRestaurantId;
    
    private GetReservationCheckResponseDto(ReservationEntity reservationEntity) throws Exception 
    {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.reservationUserId=reservationEntity.getReservationUserId();
        this.reservationRestaurantId=reservationEntity.getReservationRestaurantId();
    }
    
    public static ResponseEntity<GetReservationCheckResponseDto> success(ReservationEntity reservationEntity) throws Exception 
    {
        GetReservationCheckResponseDto responseBody = new GetReservationCheckResponseDto(reservationEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
/* /분석 완료/ */