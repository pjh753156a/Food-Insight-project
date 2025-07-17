package com.project.back.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.back.entity.FavoriteRestaurantEntity;

@Repository
public interface FavoriteRestaurantRepository extends JpaRepository<FavoriteRestaurantEntity, Integer> 
{
    /* 분석 완료 */
    boolean existsByFavoriteUserIdAndFavoriteRestaurantId(String favoriteUserId, Integer favoriteRestaurantId);

    /* 분석 시작 */
    FavoriteRestaurantEntity findByFavoriteUserIdAndFavoriteRestaurantId(String favoriteUserId, Integer favoriteRestaurantId);

    void deleteByFavoriteUserId(String userEmailId);
}
/* 분석 완료 */