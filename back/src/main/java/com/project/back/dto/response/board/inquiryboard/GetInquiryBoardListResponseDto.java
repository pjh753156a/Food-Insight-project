package com.project.back.dto.response.board.inquiryboard;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.ResponseCode;
import com.project.back.dto.response.ResponseMessage;
import com.project.back.repository.resultSet.GetInquiryBoardListResultSet;
import com.project.back.common.object.board.inquiryboard.InquiryBoardListItem;

import lombok.Getter;

@Getter
public class GetInquiryBoardListResponseDto extends ResponseDto 
{
    private List<InquiryBoardListItem> inquiryBoardList;
    
    private GetInquiryBoardListResponseDto(List<GetInquiryBoardListResultSet> resultSets) throws Exception 
    {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.inquiryBoardList= InquiryBoardListItem.getList(resultSets);
    }
    
    public static ResponseEntity<GetInquiryBoardListResponseDto> success(List<GetInquiryBoardListResultSet> resultSets)
    throws Exception 
    {
        GetInquiryBoardListResponseDto responseBody = new GetInquiryBoardListResponseDto(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
/* /분석 완료/ */