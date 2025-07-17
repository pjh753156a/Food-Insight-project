package com.project.back.dto.response.restaurant.reservation;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.back.entity.ReservationEntity;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.ResponseCode;
import com.project.back.dto.response.ResponseMessage;
import com.project.back.common.object.restaurant.RestaurantReservationListItem;

import lombok.Getter;

@Getter
public class GetReservationListResponseDto extends ResponseDto 
{
    private List<RestaurantReservationListItem> restaurantReservationList;
    
    private GetReservationListResponseDto(List<ReservationEntity> reservationEntities) throws Exception 
    {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.restaurantReservationList=RestaurantReservationListItem.getList(reservationEntities);
    }
    
    public static ResponseEntity<GetReservationListResponseDto> success(List<ReservationEntity> reservationEntities) throws Exception 
    {
        GetReservationListResponseDto responseBody = new GetReservationListResponseDto(reservationEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
/*분석완료*/