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
		<div class="header fade">
			<header class="header_tit">
				<h1 class="logo">
					<a href="./mainpage.html" class="lnk_logo" title="네이버"> <span class="spr_bi ico_n_logo">네이버</span> </a>
					<a href="./mainpage.html" class="lnk_logo" title="예약"> <span class="spr_bi ico_bk_logo">예약</span> </a>
				</h1>
				  <a class="btn_my"> <span title="예약확인">예약확인</span> </a>
			</header>
		</div>
		<div class="ct">
			<div class="ct_wrap">
				<div class="top_title review_header">
					<a href="javascript:history.back();" class="btn_back" title="이전 화면으로 이동"> <i class="fn fn-backward1"></i> </a>
					<h2><span class="title" id="head_title">클림트 인사이드</span></h2>
				</div>
				<!-- 리뷰 별점 -->
				<div class="write_act">
					<p class="title_star">별점과 이용경험을 남겨주세요.</p>
					<div class="review_rating rating_point">
						<div class="rating" id="_rating">
							<!-- 리뷰 별점들이 그려진다. -->						
						</div>
					</div>
				</div>
				<!-- //리뷰 별점 -->

				<!-- 리뷰 입력 -->
				<div class="review_contents write">
					<textarea 
						cols="30" 
						rows="10" 
						class="review_textarea"
						id="_review_textarea"
						placeholder="실 사용자의 리뷰는 상품명의 더 나은 서비스 제공과 다른 사용자들의 선택에 큰 도움이 됩니다.&#13;&#10;&#13;&#10;소중한 리뷰에 대한 감사로 네이버페이 포인트 500원을 적립해드립니다.&#13;&#10;&#13;&#10;(단, 리뷰 포인트는 ID 당 1일 최대 5건까지 지급됩니다.)"
					></textarea>
				</div>
				<!-- //리뷰 입력 -->

				<!-- 리뷰 작성 푸터 -->
				<div class="review_write_footer_wrap">
					<div class="review_write_footer">
						<label class="btn_upload" for="reviewImageFileOpenInput">
							<i class="fn fn-image1" aria-hidden="true"></i>
							<span class="text_add_photo">사진 추가</span>
						</label>
						<input type="file" class="hidden_input" id="reviewImageFileOpenInput" accept="image/*" multiple>
						<div class="guide_review">
							<span id="current_len">0</span>/<span id="max_len">400</span>
							<span>(최소5자이상)</span>
						</div>
					</div>

					<!-- 리뷰 포토 -->
					<div class="review_photos review_photos_write">
						<div class="item_preview_thumbs">
							<ul class="lst_thumb">
								<li class="item" style="display:none; width:100px;" id="img_thumb_li">
									<a class="anchor" id="_ico_del">
										<span class="spr_book ico_del">삭제</span>
									</a>
									<img width="100" alt="" class="item_thumb" id="_item_thumb">
									<span class="img_border"></span>
								</li>
							</ul>
						</div>
					</div>
					<!-- //리뷰 포토 -->
				</div>
				<!-- //리뷰 작성 푸터 -->

				<!-- 리뷰 등록 -->
				<div class="box_bk_btn">
					<button class="bk_btn" onClick="javascript:handleSubmit();"><span class="btn_txt">리뷰 등록</span></button>
				</div>
				<!-- //리뷰 등록 -->
			</div>
		</div>
	</div>
	
	<footer>
		<div class="gototop">
			<a href="javascript:scroll(0,0)" class="lnk_top"> <span class="lnk_top_text">TOP</span> </a>
		</div>
		<div id="footer" class="footer">
			<p class="dsc_footer">네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및 환불 등과 관련한 의무와 책임은 각 회원에게 있습니다.</p>
			<span class="copyright">© NAVER Corp.</span>
		</div>
	</footer>
	
	<!-- 별점 리스트를 위한 템플릿  -->
    <script type="rv-template" id="ratingItem">
        <input type="checkbox" class="rating_rdo" id="rating_rdo_{{num}}" title="{{num}}점" onClick="javascript:clickStar({{num}})">
		<span class="span"></span>
    </script>
    
	<!-- 템플릿 처리를 위한 자바스크립트 Handlebar Library -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.11/handlebars.min.js"></script>   
	
	<!-- js 파일 include -->
	<script type="text/javascript" src="static/script/common/common.js"></script>
	
	<script type="text/javascript" src="static/script/reviewWrite/reviewWrite.js"></script>
</body>
</html>