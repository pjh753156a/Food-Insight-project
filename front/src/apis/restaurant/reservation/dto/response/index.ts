import ResponseDto from "src/apis/response.dto";
import { RestaurantReservationListItem } from "src/types";

// description: 식당 예약 내역 목록 확인 Response Body DTO
export interface GetReservationListResponseDto extends ResponseDto 
{
    restaurantReservationList: RestaurantReservationListItem[];
}

// description: 식당 예약 상태 확인 Response Body DTO
export interface GetReservationCheckResponseDto extends ResponseDto
{
    reservationUserId: string
    reservationRestaurantId: number
}
/* /분석 완료/ */