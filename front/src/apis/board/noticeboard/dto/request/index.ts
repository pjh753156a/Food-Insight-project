// description: 공지 게시물 작성 Request Body DTO
export interface PostNoticeBoardRequestDto 
{
  noticeTitle: string;
  noticeContents: string;
}

// description: 공지 게시물 수정 Request Body DTO
export interface PatchNoticeBoardRequestDto 
{
  noticeTitle: string;
  noticeContents: string;
}
/*분석 완료*/