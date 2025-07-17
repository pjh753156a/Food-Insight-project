package com.project.back.common.object.restaurant;

import java.util.ArrayList;
import java.util.List;

import com.project.back.entity.RestaurantEntity;
import com.project.back.repository.resultSet.GetRestaurantFavoriteItemResultSet;

import lombok.Getter;

@Getter
public class RestaurantListItem 
{
    private Integer restaurantId;
    private String restaurantName;
    private String restaurantImage;
    private String restaurantLocation;
    private String restaurantFoodCategory;
    
    private RestaurantListItem(RestaurantEntity restaurantEntity) throws Exception 
    {
        this.restaurantId=restaurantEntity.getRestaurantId();
        this.restaurantName=restaurantEntity.getRestaurantName();
        this.restaurantImage=restaurantEntity.getRestaurantImage();
        this.restaurantLocation=restaurantEntity.getRestaurantLocation();
        this.restaurantFoodCategory=restaurantEntity.getRestaurantFoodCategory();
    }

    private RestaurantListItem(GetRestaurantFavoriteItemResultSet getRestaurantFavoriteItemResultSet) throws Exception 
    {
        this.restaurantId=getRestaurantFavoriteItemResultSet.getRestaurantId();
        this.restaurantName=getRestaurantFavoriteItemResultSet.getRestaurantName();
        this.restaurantImage=getRestaurantFavoriteItemResultSet.getRestaurantImage();
        this.restaurantLocation=getRestaurantFavoriteItemResultSet.getRestaurantLocation();
        this.restaurantFoodCategory=getRestaurantFavoriteItemResultSet.getRestaurantFoodCategory();
    }
    
    public static List<RestaurantListItem> getRestaurantList(List<RestaurantEntity> restaurantEntities) throws Exception 
    {
        List<RestaurantListItem> restaurantList = new ArrayList<>();

        for (RestaurantEntity restaurantEntity:restaurantEntities) 
        {
            RestaurantListItem restaurantListItem = new RestaurantListItem(restaurantEntity);
            restaurantList.add(restaurantListItem);
        }
        return restaurantList;
    }
    
    public static List<RestaurantListItem> getFavoriteRestaurantList(List<GetRestaurantFavoriteItemResultSet> getRestaurantFavoriteItemResultSetList) throws Exception 
    {
        List<RestaurantListItem> restaurantList = new ArrayList<>();

        for (GetRestaurantFavoriteItemResultSet getRestaurantFavoriteItemResultSet:getRestaurantFavoriteItemResultSetList) 
        {
            RestaurantListItem restaurantFavoriteListItem = new RestaurantListItem(getRestaurantFavoriteItemResultSet);
            restaurantList.add(restaurantFavoriteListItem);
        }
        return restaurantList;
    }
}
/* 분석 완료 */