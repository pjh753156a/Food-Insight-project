package com.project.back.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity(name="favoriteRestaurant")
@Table(name="Favorite_Restaurant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteRestaurantEntity 
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer favoriteNumber;
    private Integer favoriteRestaurantId;
    private String favoriteUserId; 

    public FavoriteRestaurantEntity(String userEmailId, int restaurantId) 
    {
        this.favoriteUserId = userEmailId;
        this.favoriteRestaurantId = restaurantId;
    }
}
/* 분석 완료 */