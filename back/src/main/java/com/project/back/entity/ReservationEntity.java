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

import com.project.back.dto.request.restaurant.reservation.PostReservationRequestDto;

@Entity(name="reservation")
@Table(name="reservation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationEntity 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reservationNumber;
    private Integer reservationRestaurantId;
    private Boolean reservationStatus;
    private Integer reservationPeople;
    private String reservationDate;
    private String reservationTime;
    private String reservationUserId;
    private String reservationUserName;
    private String reservationUserTelNumber;
    private String reservationRestaurantName;
    private String reservationRestaurantLocation;
    
    public ReservationEntity(PostReservationRequestDto dto, String userEmailId, int restaurantId, UserEntity userEntity, RestaurantEntity restaurantEntity) 
    {
        this.reservationStatus = true;
        this.reservationUserId = userEmailId;
        this.reservationRestaurantId = restaurantId;

        this.reservationDate = dto.getReservationDate();
        this.reservationTime = dto.getReservationTime();
        this.reservationPeople = dto.getReservationPeople();
        this.reservationUserName = userEntity.getUserName();
        this.reservationUserTelNumber = userEntity.getUserTelNumber();
        this.reservationRestaurantName = restaurantEntity.getRestaurantName();
        this.reservationRestaurantLocation = restaurantEntity.getRestaurantLocation();
    }
}
/* /분석 완료/ */