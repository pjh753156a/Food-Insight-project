package com.project.back.dto.response.restaurant.review;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.back.common.object.restaurant.RestaurantReviewListItem;
import com.project.back.dto.response.ResponseCode;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.ResponseMessage;
import com.project.back.repository.resultSet.GetRestaurantReviewListItemResultSet;

import lombok.Getter;
@Getter
public class GetReviewListResponseDto extends ResponseDto 
{
    private List<RestaurantReviewListItem> restaurantReviewList;
    
    private GetReviewListResponseDto(List<GetRestaurantReviewListItemResultSet> reviewEntities) throws Exception 
    {
        super(ResponseCode.SUCCESS,ResponseMessage.SUCCESS);
        this.restaurantReviewList=RestaurantReviewListItem.getList(reviewEntities);
    }
    
    public static ResponseEntity<GetReviewListResponseDto> success(List<GetRestaurantReviewListItemResultSet> getRestaurantReviewListItemResultSets) throws Exception 
    {
        GetReviewListResponseDto responseBody = new GetReviewListResponseDto(getRestaurantReviewListItemResultSets);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
/* /분석 완료/ */