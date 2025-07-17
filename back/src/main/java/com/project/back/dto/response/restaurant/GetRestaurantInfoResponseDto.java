package com.project.back.dto.response.restaurant;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.back.entity.RestaurantEntity;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.ResponseCode;
import com.project.back.dto.response.ResponseMessage;
import com.project.back.common.object.restaurant.RestaurantReviewListItem;
import com.project.back.repository.resultSet.GetRestaurantReviewListItemResultSet;

import lombok.Getter;

@Getter
public class GetRestaurantInfoResponseDto extends ResponseDto 
{
    private Integer restaurantId; 
    private String restaurantLat;
    private String restaurantLng;
    private String restaurantName;
    private String restaurantImage;
    private String restaurantNotice;
    private String restaurantWriterId;
    private String restaurantLocation;
    private String restaurantFeatures;
    private String restaurantTelNumber;
    private String restaurantSnsAddress;
    private String restaurantFoodCategory;
    private String restaurantOperationHours;
    private String restaurantRepresentativeMenu;
    private String restaurantBusinessRegistrationNumber;
    private List<RestaurantReviewListItem> restaurantReviewList;
    
    private GetRestaurantInfoResponseDto(RestaurantEntity restaurantEntity, List<GetRestaurantReviewListItemResultSet> reviewEntities) throws Exception 
    {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

        this.restaurantId=restaurantEntity.getRestaurantId();
        this.restaurantLat=restaurantEntity.getRestaurantLat();
        this.restaurantLng=restaurantEntity.getRestaurantLng();
        this.restaurantName=restaurantEntity.getRestaurantName();
        this.restaurantImage=restaurantEntity.getRestaurantImage();
        this.restaurantNotice=restaurantEntity.getRestaurantNotice();
        this.restaurantWriterId=restaurantEntity.getRestaurantWriterId();
        this.restaurantLocation=restaurantEntity.getRestaurantLocation();
        this.restaurantFeatures=restaurantEntity.getRestaurantFeatures();
        this.restaurantTelNumber=restaurantEntity.getRestaurantTelNumber();
        this.restaurantSnsAddress=restaurantEntity.getRestaurantSnsAddress();
        this.restaurantFoodCategory=restaurantEntity.getRestaurantFoodCategory();
        this.restaurantOperationHours=restaurantEntity.getRestaurantOperationHours();
        this.restaurantRepresentativeMenu=restaurantEntity.getRestaurantRepresentativeMenu();
        this.restaurantBusinessRegistrationNumber=restaurantEntity.getRestaurantBusinessRegistrationNumber();
        
        this.restaurantReviewList=RestaurantReviewListItem.getList(reviewEntities);
    }
    
    public static ResponseEntity<GetRestaurantInfoResponseDto> success(RestaurantEntity restaurantEntity, List<GetRestaurantReviewListItemResultSet> getRestaurantReviewListItemResultSets) throws Exception 
    {
        GetRestaurantInfoResponseDto responseBody = new GetRestaurantInfoResponseDto(restaurantEntity,getRestaurantReviewListItemResultSets);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
/* /분석 완료/ */