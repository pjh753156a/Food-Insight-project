package com.project.back.dto.response.board.noticeboard;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.ResponseCode;
import com.project.back.dto.response.ResponseMessage;
import com.project.back.repository.resultSet.GetNoticeBoardListResultSet;
import com.project.back.common.object.board.noticeboard.NoticeBoardListItem;

import lombok.Getter;

@Getter
public class GetNoticeBoardListResponseDto extends ResponseDto 
{
    private List<NoticeBoardListItem> noticeBoardList;
    
    private GetNoticeBoardListResponseDto(List<GetNoticeBoardListResultSet> resultSets) throws Exception 
    {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.noticeBoardList = NoticeBoardListItem.getList(resultSets);
    }
    
    public static ResponseEntity<GetNoticeBoardListResponseDto> success(List<GetNoticeBoardListResultSet> resultSets) throws Exception 
    {
        GetNoticeBoardListResponseDto responseBody = new GetNoticeBoardListResponseDto(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
/*분석 완료*/
