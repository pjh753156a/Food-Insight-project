import { useCookies } from 'react-cookie';
import { useNavigate, useParams } from 'react-router';
import { ChangeEvent, KeyboardEvent, useEffect, useRef, useState } from 'react';

import { useUserStore } from 'src/stores';

import ResponseDto from 'src/apis/response.dto';
import { GetReviewResponseDto } from 'src/apis/restaurant/review/dto/response';
import { PatchReviewRequestDto } from 'src/apis/restaurant/review/dto/request';

import { GetReviewDetailRequest, PatchReviewRequest } from 'src/apis/restaurant/review';

import { MAIN_ABSOLUTE_PATH, RESTAURANT_REVIEW_ABSOLUTE_DETAIL_PATH } from 'src/constant';

import './style.css';

// component //
export default function ReviewUpdate()
{
    // state //
    const [cookies] = useCookies();
    const {reviewNumber} = useParams();
    const {loginUserRole} = useUserStore();
    const [rating, setRating] = useState<number>();
    const [reviewImage, setReviewImage] = useState<string>("");
    const [reviewContents, setReviewContents] = useState<string>("");

    const contentsRef = useRef<HTMLTextAreaElement | null>(null);

    // function //
    const navigation = useNavigate();

    const PatchReviewResponse = (result: ResponseDto | null) => 
    {
        const message =
            !result ? '서버에 문제가 있습니다.' :
            result.code === 'VF' ? '필수 데이터를 입력하지 않았습니다.' :
            result.code === 'NR' ? '존재하지 않는 식당입니다.' :
            result.code === 'AF' ? '권한이 없습니다.' :
            result.code === 'DBE' ? '서버에 문제가 있습니다.' : '';

        if (!result || result.code !== 'SU') 
        {
            alert(message);
            return;
        }

        if (!reviewNumber) return;
        navigation(RESTAURANT_REVIEW_ABSOLUTE_DETAIL_PATH(reviewNumber));
    }
    
    const GetReviewDetailResponse = (result: GetReviewResponseDto | ResponseDto | null) => 
    {
        const message = 
            !result ? '서버에 문제가 있습니다.' : 
            result.code === 'DBE' ? '서버에 문제가 있습니다.' : '';
    
        if (!result || result.code !== 'SU')
        {
            alert(message);
            if (result?.code === 'AF') 
            {
                navigation(MAIN_ABSOLUTE_PATH);
                return;
            }
        return;
        }
    
        const{reviewImage,reviewContents} = result as GetReviewResponseDto;
        setReviewImage(reviewImage);
        setReviewContents(reviewContents);
    };
    
    // event handler //
    const onImageChangeHandler = (event: ChangeEvent<HTMLInputElement>) => 
    {
        const file = event.target.files?.[0];
        if (file) 
        {
            const reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onloadend = () => 
            {
                const base64String = reader.result?.toString();
                if (base64String) 
                {
                    setReviewImage(base64String);
                }
            };
        }
    }

    const onRatingChangeHandler = (event: ChangeEvent<HTMLSelectElement>) => 
    {
        if (event.target.value==="선택") { setRating(0); }

        const { value } = event.target;
        const result = Number(value);
        setRating(result);
    }

    const onContentsChangeHandler = (event: ChangeEvent<HTMLTextAreaElement>) => 
    {
        const { value } = event.target;
        setReviewContents(value);

        if(!contentsRef.current) return;
        contentsRef.current.style.height = 'auto';
        contentsRef.current.style.height = `${contentsRef.current.scrollHeight}px`;
    }

    const UpdateClickHandler = () => 
    {
        if (!rating || !reviewNumber) 
        {
            return;
        }

        const requestBody: PatchReviewRequestDto = 
        {
            reviewImage: reviewImage,
            rating: rating,
            reviewContents: reviewContents
        }

        PatchReviewRequest(reviewNumber, requestBody, cookies.accessToken).then(PatchReviewResponse);
    }
    
    const onKeyPressHandler = (event: KeyboardEvent<HTMLTextAreaElement>) => 
    {
        if (event.key === 'Enter') 
        {
            UpdateClickHandler();
        }
    };
    
    // effect //
    let effectFlag = false;

    useEffect(()=>{
        if (!reviewNumber || !cookies.accessToken) return;
        if (!loginUserRole) return;
        if (effectFlag) return;
        effectFlag = true;

        if (loginUserRole !== 'ROLE_USER') 
        {
            navigation(MAIN_ABSOLUTE_PATH);
            return;
        }

        GetReviewDetailRequest(reviewNumber,cookies.accessToken).then(GetReviewDetailResponse);
    },[])

    const ButtonClass = `${rating ? 'review-primary' : 'review-disable'}-button`;

    // render //
    return (
        <>
            <div className="review-write-title">리뷰 수정</div>
            <div className="review-write-box">
                <input type="file" accept="image/*" onChange={onImageChangeHandler} className="review-image-file-input"/>
                {reviewImage && (
                    <img src={reviewImage}  style={{ maxWidth: '100px', maxHeight: '100px' }} className="review-image-file"/>
                )} 
                <div className='review-grade'>평점</div>
                <div id="review-rating-box">
                    <select id="review-rating" name="review-rating" defaultValue={rating} onChange={onRatingChangeHandler}>
                        <option value="선택">선택</option>
                        <option value="1.0">1.0</option>
                        <option value="1.5">1.5</option>
                        <option value="2.0">2.0</option>
                        <option value="2.5">2.5</option>
                        <option value="3.0">3.0</option>
                        <option value="3.5">3.5</option>
                        <option value="4.0">4.0</option>
                        <option value="4.5">4.5</option>
                        <option value="5.0">5.0</option>
                    </select>
                </div>
                <div className='review-write-contents-box'>
                    <textarea ref={contentsRef} className='review-write-contents-textarea' placeholder='내용을 입력해주세요. / 300자' maxLength={300} value={reviewContents} onChange={onContentsChangeHandler} onKeyPress={onKeyPressHandler}/>
                </div>
                <div className="review-registered-button-box">
                    <button onClick={UpdateClickHandler} className={ButtonClass}>수정하기</button>
                </div>
            </div>
        </>
    )
}
{/* /분석 완료/ */}