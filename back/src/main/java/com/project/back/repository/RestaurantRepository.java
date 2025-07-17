package com.project.back.repository;

import java.util.List;

import com.project.back.entity.RestaurantEntity;
import com.project.back.repository.resultSet.GetRestaurantFavoriteItemResultSet;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity,Integer> 
{
    // Object restaurantEntity = null;
    boolean existsByRestaurantId(Integer restaurantId);
    RestaurantEntity findByRestaurantId(Integer restaurantId);
    boolean existsByRestaurantWriterId(String restaurantWriterId);
    RestaurantEntity findByRestaurantWriterId(String restaurantWriterId);
    RestaurantEntity getRestaurantIdByRestaurantWriterId(String restaurantWriterId);
    RestaurantEntity findByRestaurantWriterIdAndRestaurantId(String userEmailId, Integer restaurantId);
    List<RestaurantEntity> findByRestaurantNameContainingOrderByRestaurantIdDesc(String searchWord);

    
    @Query(value=
        "SELECT "
            + "r.restaurant_id as restaurantId, "
            + "r.restaurant_image as restaurantImage, "
            + "r.restaurant_name as restaurantName, "
            + "r.restaurant_food_category as restaurantFoodCategory, "
            + "r.restaurant_location as restaurantLocation "
        + "FROM restaurant r "
        + "WHERE restaurant_id "
        + "IN "
        + "(SELECT favorite_restaurant_id FROM favorite_restaurant WHERE `favorite_user_id` = :userEmailId)"
        + "ORDER BY r.restaurant_id ", 
        nativeQuery=true
    )
    List<GetRestaurantFavoriteItemResultSet> getFavoriteList(@Param("userEmailId") String favoriteUserId);
}
/* /분석 완료/ */