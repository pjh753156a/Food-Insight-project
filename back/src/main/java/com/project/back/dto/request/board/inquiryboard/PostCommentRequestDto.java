package com.project.back.dto.request.board.inquiryboard;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class PostCommentRequestDto 
{
    @NotBlank
    private String inquiryComment;
}
/*분석 완료*/
