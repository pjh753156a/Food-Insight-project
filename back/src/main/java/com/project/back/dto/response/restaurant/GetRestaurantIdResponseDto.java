package com.project.back.dto.response.restaurant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.project.back.entity.RestaurantEntity;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.ResponseCode;
import com.project.back.dto.response.ResponseMessage;

import lombok.Getter;

@Getter
public class GetRestaurantIdResponseDto extends ResponseDto 
{
    private Integer restaurantId; 

    private GetRestaurantIdResponseDto(RestaurantEntity restaurantEntity)
    throws Exception 
    {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.restaurantId = restaurantEntity.getRestaurantId();
    }
    
    public static ResponseEntity<GetRestaurantIdResponseDto> success(RestaurantEntity restaurantEntity) throws Exception 
    {
        GetRestaurantIdResponseDto responseBody = new GetRestaurantIdResponseDto(restaurantEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
/* /분석 완료/ */
