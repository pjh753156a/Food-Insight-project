import ResponseDto from 'src/apis/response.dto';
import { GetMyInfoResponseDto, GetUserInfoResponseDto } from 'src/apis/user/dto/response';
import { DeleteUserRequestDto, PatchUserInfoRequestDto } from 'src/apis/user/dto/request';

import axios from 'axios';
import { bearerAuthorization, requestErrorHandler, requestHandler } from 'src/apis/index';

import { DELETE_INFO_DELETE_REQUEST_URL, GET_MY_INFO_URL, GET_SIGN_IN_USER_REQUEST_URL, PATCH_INFO_UPDATE_REQUEST_URL } from 'src/constant';

// function: 로그인 유저 정보 불러오기 API 함수 
export const getSignInUserRequest = async (accessToken: string) => 
{
    const result = await axios.get(GET_SIGN_IN_USER_REQUEST_URL, bearerAuthorization(accessToken))
        .then(requestHandler<GetUserInfoResponseDto>) 
        .catch(requestErrorHandler);
    return result;
};

// function: 회원정보 수정 API 함수 
export const patchUserInfoRequest = async (userEmailId: string, requestBody: PatchUserInfoRequestDto,  accessToken: string) => 
{
    const result = await axios.patch(PATCH_INFO_UPDATE_REQUEST_URL(userEmailId), requestBody, bearerAuthorization(accessToken))
        .then(requestHandler<ResponseDto>)
        .catch(requestErrorHandler);
    return result;
};

// function: 회원탈퇴 API 함수 
export const deleteUserRequest = async (userEmailId: string, requestBody: DeleteUserRequestDto, accessToken: string) => 
{
    const result = await axios.post(DELETE_INFO_DELETE_REQUEST_URL(userEmailId), requestBody, bearerAuthorization(accessToken))
        .then(requestHandler<ResponseDto>)
        .catch(requestErrorHandler);
    return result;
};

// function: 내 정보 불러오기 API 함수 
export const getMyInfoRequest = async (accessToken: string) => 
{
    const result = await axios
        .get(GET_MY_INFO_URL, bearerAuthorization(accessToken))
        .then(requestHandler<GetMyInfoResponseDto>)
        .catch(requestErrorHandler);
    return result;
};
/* /분석 완료/ */