package com.project.back.dto.request.restaurant;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class PostRestaurantInfoRequestDto 
{
    @NotNull
    private String restaurantLat;
    @NotBlank
    private String restaurantName;
    @NotBlank
    private String restaurantImage;
    @NotBlank
    private String restaurantLocation;
    @NotBlank
    private String restaurantFoodCategory;
    @NotNull
    private String restaurantLng;
    private String restaurantNotice;
    private String restaurantFeatures;
    private String restaurantTelNumber;
    private String restaurantSnsAddress;
    private String restaurantOperationHours;
    private String restaurantRepresentativeMenu;
}
/*분석완료*/