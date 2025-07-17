package com.project.back.common.object.restaurant;

import java.util.List;
import java.util.ArrayList;

import com.project.back.common.util.ChangeDateFormatUtil;
import com.project.back.repository.resultSet.GetRestaurantReviewListItemResultSet;

import lombok.Getter;

@Getter
public class RestaurantReviewListItem 
{
    private double rating;
    private Integer reviewNumber;
    private Integer reviewRestaurantId;
    private String reviewDate;
    private String reviewImage;
    private String reviewContents;
    private String reviewWriterNickname;
    private String reviewRestaurantName;
    
    private RestaurantReviewListItem(GetRestaurantReviewListItemResultSet getRestaurantReviewListItemResultSet) throws Exception 
    {
        this.rating = getRestaurantReviewListItemResultSet.getRating();
        this.reviewImage = getRestaurantReviewListItemResultSet.getReviewImage();
        this.reviewNumber = getRestaurantReviewListItemResultSet.getReviewNumber();
        this.reviewContents = getRestaurantReviewListItemResultSet.getReviewContents();
        this.reviewRestaurantId = getRestaurantReviewListItemResultSet.getReviewRestaurantId();
        this.reviewWriterNickname = getRestaurantReviewListItemResultSet.getReviewWriterNickname();
        this.reviewRestaurantName = getRestaurantReviewListItemResultSet.getReviewRestaurantName(); 

        if (getRestaurantReviewListItemResultSet.getReviewDate()!=null) 
        {
            String writeDatetime = ChangeDateFormatUtil.changeYYMMDD(getRestaurantReviewListItemResultSet.getReviewDate());
            this.reviewDate = writeDatetime;
        }
    }
    
    public static List<RestaurantReviewListItem> getList(List<GetRestaurantReviewListItemResultSet> getRestaurantReviewListItemResultSetList) throws Exception 
    {
        List<RestaurantReviewListItem> restaurantReviewList = new ArrayList<>();

        for (GetRestaurantReviewListItemResultSet getRestaurantReviewListItemResultSet:getRestaurantReviewListItemResultSetList) 
        {
            RestaurantReviewListItem restaurantReviewListItem = new RestaurantReviewListItem(getRestaurantReviewListItemResultSet);
            restaurantReviewList.add(restaurantReviewListItem);
        }
        return restaurantReviewList;
    }
}
/* /분석 완료/ */