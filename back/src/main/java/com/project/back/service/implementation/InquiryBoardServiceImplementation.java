package com.project.back.service.implementation;

import java.util.List;

import com.project.back.entity.UserEntity;
import com.project.back.entity.InquiryBoardEntity;
import com.project.back.service.InquiryBoardService;

import com.project.back.repository.UserRepository;
import com.project.back.repository.InquiryBoardRepository;
import com.project.back.repository.resultSet.GetInquiryBoardListResultSet;

import com.project.back.dto.request.board.inquiryboard.PostCommentRequestDto;
import com.project.back.dto.request.board.inquiryboard.PatchInquiryBoardRequestDto;
import com.project.back.dto.request.board.inquiryboard.PostInquiryBoardRequestDto;

import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.board.inquiryboard.GetInquiryBoardListResponseDto;
import com.project.back.dto.response.board.inquiryboard.GetInquiryBoardResponseDto;
import com.project.back.dto.response.board.inquiryboard.GetSearchInquiryBoardListResponseDto;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

@Service
@RequiredArgsConstructor
public class InquiryBoardServiceImplementation implements InquiryBoardService 
{
    private final UserRepository userRepository;
    private final InquiryBoardRepository inquiryBoardRepository;

    @Override
    public ResponseEntity<ResponseDto> postBoard(PostInquiryBoardRequestDto dto, String userEmailId) 
    {
        try 
        {
            boolean isExistUser = userRepository.existsByUserEmailId(userEmailId);
            if (!isExistUser) return ResponseDto.authenticationFailed();

            InquiryBoardEntity inquiryBoardEntity = new InquiryBoardEntity(dto, userEmailId);
            inquiryBoardRepository.save(inquiryBoardEntity);
        } 
        catch(Exception exception) 
        {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> postComment(PostCommentRequestDto dto, int inquiryNumber) 
    {
        try 
        {
            InquiryBoardEntity inquiryBoardEntity = inquiryBoardRepository.findByInquiryNumber(inquiryNumber);
            if (inquiryBoardEntity == null) return ResponseDto.noExistInquiryBoard();

            boolean status = inquiryBoardEntity.getStatus();
            if (status) return ResponseDto.writtenComment();

            String comment = dto.getInquiryComment();
            inquiryBoardEntity.setStatus(true);
            inquiryBoardEntity.setInquiryComment(comment);
            inquiryBoardRepository.save(inquiryBoardEntity);
        } 
        catch(Exception exception) 
        {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }
    
    @Override
    public ResponseEntity<? super GetInquiryBoardListResponseDto> getInquiryBoardList() 
    {
        try 
        {
            List<GetInquiryBoardListResultSet> resultSets = inquiryBoardRepository.getInquiryBoardList();
            return GetInquiryBoardListResponseDto.success(resultSets);
        } 
        catch(Exception exception) 
        {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super GetSearchInquiryBoardListResponseDto> getSearchInquiryBoardList(String searchWord) 
    {
        try 
        {
            List<GetInquiryBoardListResultSet> resultSets = inquiryBoardRepository.getInquirySearchBoardList(searchWord);
            return GetSearchInquiryBoardListResponseDto.success(resultSets);
        }
        catch(Exception exception) 
        {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }
    
    @Override
    public ResponseEntity<? super GetInquiryBoardResponseDto> getInquiryBoard(int inquiryNumber) 
    {
        try 
        {
            InquiryBoardEntity inquiryBoardEntity = inquiryBoardRepository.findByInquiryNumber(inquiryNumber);
            if (inquiryBoardEntity == null) return ResponseDto.noExistInquiryBoard();

            String userEmailId = inquiryBoardEntity.getInquiryWriterId();
            UserEntity userEntity = userRepository.findByUserEmailId(userEmailId);
            if (userEntity == null) return ResponseDto.authorizationFailed();

            String nickname = userEntity.getNickname();

            return GetInquiryBoardResponseDto.success(inquiryBoardEntity, nickname);
        } 
        catch(Exception exception)
        {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<ResponseDto> patchInquiryBoard(PatchInquiryBoardRequestDto dto, int inquiryNumber, String userEmailId) 
    {
        try 
        {
            InquiryBoardEntity inquiryBoardEntity = inquiryBoardRepository.findByInquiryNumber(inquiryNumber);
            if (inquiryBoardEntity == null) return ResponseDto.noExistInquiryBoard();

            String writerId = inquiryBoardEntity.getInquiryWriterId();
            boolean isWriter = userEmailId.equals(writerId);
            if (!isWriter) return ResponseDto.authorizationFailed();

            boolean status = inquiryBoardEntity.getStatus();
            if (status) return ResponseDto.writtenComment();

            inquiryBoardEntity.update(dto);
            inquiryBoardRepository.save(inquiryBoardEntity);
        } 
        catch(Exception exception) 
        {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> deleteInquiryBoard(int inquiryNumber, String userEmailId) 
    {
        try 
        {
            InquiryBoardEntity inquiryBoardEntity = inquiryBoardRepository.findByInquiryNumber(inquiryNumber);
            if (inquiryBoardEntity == null) return ResponseDto.noExistInquiryBoard();
        
            String writerId = inquiryBoardEntity.getInquiryWriterId();
            boolean isWriter = userEmailId.equals(writerId);
            if (!isWriter) return ResponseDto.authorizationFailed();

            inquiryBoardRepository.delete(inquiryBoardEntity);
        }
        catch(Exception exception) 
        {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }
}
/* /분석 완료/ */