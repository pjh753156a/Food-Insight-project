package com.project.back.common.object.restaurant;

import java.util.List;
import java.util.ArrayList;

import com.project.back.entity.ReservationEntity;
import com.project.back.common.util.ChangeDateFormatUtil;

import lombok.Getter;

@Getter
public class RestaurantReservationListItem 
{
    private boolean reservationStatus;
    private Integer reservationNumber;
    private Integer reservationPeople;
    private Integer reservationRestaurantId;
    private String writeDate;
    private String reservationDate;
    private String reservationTime;
    private String reservationUserName;
    private String reservationRestaurantName;
    private String reservationRestaurantLocation;
    
    private RestaurantReservationListItem(ReservationEntity reservationEntity) throws Exception 
    {
        this.reservationStatus = true;
        this.reservationNumber = reservationEntity.getReservationNumber();
        this.reservationUserName = reservationEntity.getReservationUserName();
        this.reservationRestaurantId = reservationEntity.getReservationRestaurantId();
        this.reservationRestaurantName = reservationEntity.getReservationRestaurantName();
        this.reservationRestaurantLocation = reservationEntity.getReservationRestaurantLocation();
        this.reservationPeople = reservationEntity.getReservationPeople();
        
        writeDate = reservationEntity.getReservationTime();
        this.reservationTime = writeDate;
        
        writeDate = reservationEntity.getReservationDate();
        this.reservationDate = writeDate;
    }
    
    public static List<RestaurantReservationListItem> getList(List<ReservationEntity> reservationEntities) throws Exception 
    {
        List<RestaurantReservationListItem> restaurantReservationList = new ArrayList<>();

        for (ReservationEntity reservationEntity:reservationEntities) 
        {
            RestaurantReservationListItem restaurantReservationListItem = new RestaurantReservationListItem(reservationEntity);
            restaurantReservationList.add(restaurantReservationListItem);
        }
        return restaurantReservationList;
    }
}
/*분석완료*/