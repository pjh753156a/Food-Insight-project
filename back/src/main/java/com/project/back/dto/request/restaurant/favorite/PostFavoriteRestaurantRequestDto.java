package com.project.back.dto.request.restaurant.favorite;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class PostFavoriteRestaurantRequestDto 
{
    @NotBlank
    private String favoriteUserEmailId;
    @NotBlank
    private Integer favoriteRestaurantId;
}
/* 분석 완료 */