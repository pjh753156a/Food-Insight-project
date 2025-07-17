import { Outlet, useLocation, useNavigate } from 'react-router';

import { MAIN_ABSOLUTE_PATH } from "src/constant";

import "./style.css";

// component: 로그인, 회원가입 공통부분 //
export default function Authentication() 
{
    // state //
    const navigation = useNavigate();
    const { pathname } = useLocation();
    
    // function //
    const onLogoClickHandler = () => 
    {
        if (pathname === MAIN_ABSOLUTE_PATH) 
        {
            window.location.reload();
        } 
        else 
        {
            navigation(MAIN_ABSOLUTE_PATH);
        }
    }
    
    // render //
    return (
        <div id="authentication-wrapper">
            <div className='authentication-head-box'>
                <div className='authentication-title' onClick={onLogoClickHandler}>{"Food Insight"}</div>
            </div>
            <Outlet />
        </div>
    );
}
{/* 분석 완료 */}