package com.project.back.dto.response.restaurant.favorite;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.ResponseCode;
import com.project.back.dto.response.ResponseMessage;
import com.project.back.common.object.restaurant.RestaurantListItem;
import com.project.back.repository.resultSet.GetRestaurantFavoriteItemResultSet;

import lombok.Getter;

@Getter
public class GetFavoriteRestaurantListResponseDto extends ResponseDto
{
    private List<RestaurantListItem> restaurantFavoriteList;

    private GetFavoriteRestaurantListResponseDto(List<GetRestaurantFavoriteItemResultSet> favoriteRestaurantEntities) throws Exception 
    {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.restaurantFavoriteList = RestaurantListItem.getFavoriteRestaurantList(favoriteRestaurantEntities);
    }
    
    public static ResponseEntity<GetFavoriteRestaurantListResponseDto> success(List<GetRestaurantFavoriteItemResultSet> getRestaurantFavoriteItemResultSets) throws Exception 
    {
        GetFavoriteRestaurantListResponseDto responseBody = new GetFavoriteRestaurantListResponseDto(getRestaurantFavoriteItemResultSets);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
/* 분석 완료 */