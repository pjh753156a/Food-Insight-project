import ResponseDto from "src/apis/response.dto";
import { InquiryBoardListItem } from "src/types";

// description: 문의 게시물 목록 확인 Response Body DTO
export interface GetInquiryBoardListResponseDto extends ResponseDto 
{
  inquiryBoardList: InquiryBoardListItem[];
}

// description: 검색 문의 게시물 목록 확인 Response Body DTO
export interface GetSearchInquiryBoardListResponseDto extends ResponseDto 
{
  inquiryBoardList: InquiryBoardListItem[];
}

// description: 문의 게시물 확인 Response Body DTO
export interface GetInquiryBoardResponseDto extends ResponseDto 
{
  inquiryNumber: number;
  status:boolean;
  inquiryPublic: boolean;
  inquiryTitle: string;
  inquiryWriterId: string;
  inquiryWriterNickname: string;
  inquiryWriteDatetime: string;
  inquiryContents: string;
  inquiryComment: string | null;
}
/* /분석 완료/ */

// description: 문의 게시물 목록 확인 Response Body DTO
export interface GetInquiryBoardListItem 
{
  inquiryNumber: number;
  status: boolean;
  inquiryPublic: boolean;
  inquiryTitle: string;
  inquiryWriterId: string;
  inquiryWriteDatetime: string;
}