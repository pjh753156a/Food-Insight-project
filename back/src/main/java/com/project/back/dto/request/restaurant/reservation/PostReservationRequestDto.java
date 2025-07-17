package com.project.back.dto.request.restaurant.reservation;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class PostReservationRequestDto 
{
    @NotBlank
    private String reservationDate;
    @NotBlank
    private String reservationTime;
    @NotNull
    private Integer reservationPeople;
}
/* /분석 완료/ */