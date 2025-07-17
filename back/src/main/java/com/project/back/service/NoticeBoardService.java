package com.project.back.service;

import org.springframework.http.ResponseEntity;

import com.project.back.dto.request.board.noticeboard.PostNoticeBoardRequestDto;
import com.project.back.dto.request.board.noticeboard.PatchNoticeBoardRequestDto;

import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.board.noticeboard.GetNoticeBoardResponseDto;
import com.project.back.dto.response.board.noticeboard.GetNoticeBoardListResponseDto;
import com.project.back.dto.response.board.noticeboard.GetSearchNoticeBoardResponseDto;

public interface NoticeBoardService 
{
    ResponseEntity<ResponseDto> postBoard(PostNoticeBoardRequestDto dto, String userEmailId);

    ResponseEntity<? super GetNoticeBoardListResponseDto> getNoticeBoardList();
    ResponseEntity<? super GetNoticeBoardResponseDto> getNoticeBoard(int noticeNumber);
    ResponseEntity<? super GetSearchNoticeBoardResponseDto> getSearchNoticeBoardList(String searchWord);

    ResponseEntity<ResponseDto> patchNoticeBoard(PatchNoticeBoardRequestDto dto, int noticeNumber, String userEmailId);
    ResponseEntity<ResponseDto> increaseViewCount(int noticeNumber);

    ResponseEntity<ResponseDto> deleteNoticeBoard(int noticeNumber, String userEmailId);
}
/*분석 완료*/
