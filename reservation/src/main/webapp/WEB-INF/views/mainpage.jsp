<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="description" content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
	<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
	<title>네이버 예약</title>
	
	<!-- CSS import -->
    <link href="./static/css/style.css" rel="stylesheet">
</head>

<body>
	<div id="container">
        <div class="header">
            <header class="header_tit">
                <h1 class="logo">
                    <a href="" class="lnk_logo" title="네이버"> <span class="spr_bi ico_n_logo">네이버</span> </a>
                    <a class="lnk_logo" title="예약"> <span class="spr_bi ico_bk_logo">예약</span> </a>
                </h1>
                <a onClick="handleLinkToBookingLoginPageAndMyReservationPage();" class="btn_my" id="_btn_my"> 
                	<span class="viewReservation" title="예약확인" id="my_email" style="font-size:15px;">예약확인</span> 
                </a>
                <a onClick="handleLogout();" class="logout_btn hide" id="_logout_btn">
                	<span>로그아웃</span>
                </a>
            </header>
        </div>
        <hr>
        <div class="event">
            <div class="section_visual">
                <div class="group_visual">
                    <div class="container_visual">
                        <div class="prev_e" style="display:none;">
                            <div class="prev_inn">
                                <a class="btn_pre_e" title="이전"> <i class="spr_book_event spr_event_pre">이전</i> </a>
                            </div>
                        </div>
                        <div class="nxt_e" style="display:none;">
                            <div class="nxt_inn">
                                <a class="btn_nxt_e" title="다음"> <i class="spr_book_event spr_event_nxt">다음</i> </a>
                            </div>
                        </div>
                        <div>
                            <div class="container_visual">                             
                                <ul class="visual_img" id="promotions">
                                <!-- 프로모션 슬라이딩 영역: 이미지 (type = 'th')를 순차적으로 노출 -->
                                </ul>
                            </div>
                            <span class="nxt_fix" style="display:none;"></span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="section_event_tab" id="_section_event_tab">
                <ul class="event_tab_lst tab_lst_min" id="category_tab">
                 	<!-- 카테고리 탭 영역! -->
                </ul>
            </div>
            <div class="section_event_lst">
                <p class="event_lst_txt">바로 예매 가능한 행사가 <span class="pink" id="products_count">10개</span> 있습니다</p>
                <div class="wrap_event_box">
                    <!-- [D] lst_event_box 가 2컬럼으로 좌우로 나뉨, 더보기를 클릭할때마다 좌우 ul에 li가 추가됨 -->
                    <ul class="lst_event_box" id="left_product">
                        <!-- 상품 왼쪽 영역! -->
                    </ul>
                    <ul class="lst_event_box" id="right_product">
                        <!-- 상품 오른쪽 영역! -->
                    </ul>
                    <!-- 더보기 -->
                    <div class="more" id="more_btn">
                        <button class="btn"><span>더보기</span></button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <footer>
        <div class="gototop">
            <a href="javascript:scroll(0,0)" class="lnk_top"> 
            	<span class="lnk_top_text">TOP</span> 
            </a>
        </div>
        <div class="footer">
            <p class="dsc_footer">네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및 환불 등과 관련한 의무와 책임은 각 회원에게 있습니다.</p>
            <span class="copyright">© NAVER Corp.</span>
        </div>
    </footer>

	<!-- 프로모션 리스트 슬라이드를 위한 템플릿  -->
    <script type="rv-template" id="promotionItem">
    <li id = "{{id}}" class="item promotion" style="background-image: url(static/img/{{productImageUrl}});">
        <a> <span class="img_btm_border"></span> <span class="img_right_border"></span> <span class="img_bg_gra"></span>
            <div class="event_txt">
                <h4 class="event_txt_tit"></h4>
                <p class="event_txt_adr"></p>
                <p class="event_txt_dsc"></p>
            </div>
        </a>
    </li>
    </script>
    
    <!-- 카테고리 탭을 위한 템플릿  -->
    <script type="rv-template" id="categoryItem">
		<li class="item" data-category="{{id}}">
        	<a class="anchor" id="category{{id}}"> <span>{{name}}</span> </a>
        </li>
	</script>
	
	<!-- 상품 리스트를 위한 템플릿  -->
    <script type="rv-template" id="productItem">
        <li class="item">
            <a href="detail?display_info_id={{displayInfoId}}" class="item_book">
                <div class="item_preview">
                    <img alt="{{productDescription}}" class="img_thumb" src="static/img/{{productImageUrl}}">
                    <span class="img_border"></span>
                </div>
                <div class="event_txt">
                    <h4 class="event_txt_tit"> <span>{{productDescription}}</span> <small class="sm">{{placeName}}</small> </h4>
                    <p class="event_txt_dsc">{{productContent}}</p>
                </div>
            </a>
        </li>
    </script>
    
	<!-- 템플릿 처리를 위한 자바스크립트 Handlebar Library -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.11/handlebars.min.js"></script>
    
    <!-- js 파일 include -->
	<script type="text/javascript" src="static/script/common/common.js"></script>
	<script type="text/javascript" src="static/script/mainpage/promotion.js"></script>
	<script type="text/javascript" src="static/script/mainpage/category.js"></script>
	<script type="text/javascript" src="static/script/mainpage/product.js"></script>
	<script type="text/javascript" src="static/script/mainpage/mainpage.js"></script>
</body>

</html>