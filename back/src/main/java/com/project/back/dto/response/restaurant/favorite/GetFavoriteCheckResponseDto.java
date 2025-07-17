package com.project.back.dto.response.restaurant.favorite;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.ResponseCode;
import com.project.back.dto.response.ResponseMessage;
import com.project.back.entity.FavoriteRestaurantEntity;

import lombok.Getter;

@Getter
public class GetFavoriteCheckResponseDto extends ResponseDto 
{
    private String favoriteUserId; 
    private Integer favoriteRestaurantId;

    private GetFavoriteCheckResponseDto(FavoriteRestaurantEntity favoriteRestaurantEntity) throws Exception 
    {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.favoriteUserId=favoriteRestaurantEntity.getFavoriteUserId();
        this.favoriteRestaurantId=favoriteRestaurantEntity.getFavoriteRestaurantId();
    }
    
    public static ResponseEntity<GetFavoriteCheckResponseDto> success(FavoriteRestaurantEntity favoriteRestaurantEntity) throws Exception 
    {
        GetFavoriteCheckResponseDto responseBody = new GetFavoriteCheckResponseDto(favoriteRestaurantEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
/* 분석 완료 */