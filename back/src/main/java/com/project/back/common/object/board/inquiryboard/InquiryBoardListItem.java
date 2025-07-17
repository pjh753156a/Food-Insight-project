package com.project.back.common.object.board.inquiryboard;

import java.util.List;
import java.util.ArrayList;

import com.project.back.common.util.ChangeDateFormatUtil;
import com.project.back.repository.resultSet.GetInquiryBoardListResultSet;

import lombok.Getter;

@Getter
public class InquiryBoardListItem 
{
    private boolean status;
    private boolean inquiryPublic;
    private Integer inquiryNumber;
    private String inquiryTitle;
    private String inquiryWriterId;
    private String inquiryWriteDatetime;
    private String inquiryWriterNickname;

    private InquiryBoardListItem(GetInquiryBoardListResultSet resultSets) throws Exception 
    {
        this.status = resultSets.getStatus() == 1;
        this.inquiryTitle = resultSets.getInquiryTitle();
        this.inquiryNumber = resultSets.getInquiryNumber();
        this.inquiryPublic = resultSets.getInquiryPublic() == 1;
        this.inquiryWriterId = resultSets.getInquiryWriterId();
        this.inquiryWriterNickname = resultSets.getInquiryWriterNickname();

        String writeDate = ChangeDateFormatUtil.changeYYMMDD(resultSets.getInquiryWriteDatetime());
        this.inquiryWriteDatetime = writeDate;
    }
    
    public static List<InquiryBoardListItem> getList(List<GetInquiryBoardListResultSet> resultSets) throws Exception 
    {
        List<InquiryBoardListItem> inquiryBoardList = new ArrayList<>();

        for (GetInquiryBoardListResultSet resultSet :resultSets) 
        {
            InquiryBoardListItem inquiryBoardListItem = new InquiryBoardListItem(resultSet);
            inquiryBoardList.add(inquiryBoardListItem);
        }
        return inquiryBoardList;
    }
}
/* /분석 완료/ */