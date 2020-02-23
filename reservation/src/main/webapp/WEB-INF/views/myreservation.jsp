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
					<a href="/reservation" class="lnk_logo" title="네이버"> <span class="spr_bi ico_n_logo">네이버</span> </a>
					<a class="lnk_logo" title="예약"> <span class="spr_bi ico_bk_logo">예약</span> </a>
				</h1>
				<a href="" class="btn_my" id="_btn_my"> 
					<span title="내예약" class="viewReservation" id="my_email" style="font-size:15px;"></span> 
				</a>
				<a onClick="handleLogout();" class="logout_btn hide" id="_logout_btn">
                	<span>로그아웃</span>
                </a>
			</header>
		</div>
		<hr>
		<div class="ct">
			<div class="section_my">
				<!-- 예약 현황 -->
				<div class="my_summary" id="_my_summary">
					<ul class="summary_board" id="_summary_board">
						<!-- 나의 예약 요약 정보가 탭으로 그려지는 부분이다. -->
					</ul>
				</div>
				<!--// 예약 현황 -->

				<!-- 내 예약 리스트 -->
				<div class="wrap_mylist">
					<ul class="list_cards" ng-if="bookedLists.length > 0">						
						<li class="card confirmed" id="_confirmed">
							<!-- 예약 확정된 리스트가 뿌려지는 부분이다. -->
						</li>
						<!-- 예약 리스트 없음 -->
						<div class="err hide" id="err_confirmed"> <i class="spr_book ico_info_nolist"></i>
							<h1 class="tit">예약 리스트가 없습니다</h1>
						</div>
						<li class="card used" id="_used">
							<!-- 이용 완료된 리스트가 뿌려지는 부분이다. -->
						</li>
						<!-- 예약 리스트 없음 -->
						<div class="err hide" id="err_used"> <i class="spr_book ico_info_nolist"></i>
							<h1 class="tit">예약 리스트가 없습니다</h1>
						</div>
						<li class="card used cancel" id="_cancel">
							<!-- 취소된 예약 리스트가 뿌려지는 부분이다. -->														
						</li>
						<!-- 예약 리스트 없음 -->
						<div class="err hide" id="err_cancel"> <i class="spr_book ico_info_nolist"></i>
							<h1 class="tit">예약 리스트가 없습니다</h1>
						</div>
						</ul>
					</div>
				</div>
			</div>
			<hr>
		</div>
		<footer>
			<div class="gototop">
				<a href="javascript:scroll(0,0)" class="lnk_top"> 
					<span class="lnk_top_text">TOP</span> 
				</a>
			</div>
			<div id="footer" class="footer">
				<p class="dsc_footer">네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및 환불 등과 관련한 의무와 책임은 각 회원에게 있습니다.</p>
				<span class="copyright">© NAVER Corp.</span>
			</div>
		</footer>

		<!-- 취소 팝업 -->
		<!-- [D] 활성화 display:block, 아니오 버튼 or 닫기 버튼 클릭 시 숨김 display:none; -->
		<div class="popup_booking_wrapper" style="display:none;">
			<div class="dimm_dark" style="display:block"></div>
			<div class="popup_booking refund">
				<h1 class="pop_tit">
					<span>서비스명/상품명</span>
					<small class="sm">2000.0.00.(월)2000.0.00.(일)</small>
				</h1>
				<div class="nomember_alert">
					<p>취소하시겠습니까?</p>
				</div>
				<div class="pop_bottom_btnarea">
					<div class="btn_gray">
						<a class="btn_bottom"><span>아니오</span></a>
					</div>
					<div class="btn_green">
						<a class="btn_bottom"><span>예</span></a>
					</div>
				</div>
				<!-- 닫기 -->
				<a class="popup_btn_close" title="close">
					<i class="spr_book2 ico_cls"></i>
				</a>
				<!--// 닫기 -->
			</div>
		</div>
		<!--// 취소 팝업 -->
		
		
	<!-- 나의 예약 요약정보 카테고리 탭을 위한 템플릿  -->
    <script type="rv-template" id="reserveCategoryItem">
		<li class="item" data-category="{{id}}">
			<!--[D] 선택 후 .on 추가 link_summary_board -->
			<a class="link_summary_board" id="reserve_category_{{id}}"> 
				<i class="spr_book2 ico_book2"></i> 
				<em class="tit">{{category}}</em> 
				<span class="figure">{{count}}</span> 
			</a>
		</li>
	</script>
	
	<!-- 예약 리스트 아이템을 위한 템플릿  -->
    <script type="rv-template" id="reservationItem">
		<article class="card_item">
			<a class="link_booking_details">
				<div class="card_body">
					<div class="left"></div>
					
					<div class="middle">
						<div class="card_detail">
							<em class="booking_number">No.{{reservationInfoId}}</em>
							<h4 class="tit">{{displayInfo.productDescription}}</h4>
							<ul class="detail">
								<li class="item">
									<span class="item_tit">일정</span>
									<em class="item_dsc">
										{{reservationDate}}
									</em>
								</li>

								<li class="item">
									<span class="item_tit">내역</span>
									<em class="item_dsc">
										내역이 없습니다.
									</em>
								</li>
														
								<li class="item">
									<span class="item_tit">장소</span>
									<em class="item_dsc">
										{{displayInfo.placeName}}
									</em>
								</li>

								<li class="item">
									<span class="item_tit">전화</span>
									<em class="item_dsc">
										{{displayInfo.telephone}}
									</em>
								</li>
							</ul>

							<div class="price_summary">
								<span class="price_tit">결제 예정금액</span>
								<em class="price_amount">
									<span>{{totalPrice}}</span>
									<span class="unit">원</span>
								</em>
							</div>
													
							<div class="booking_cancel" onClick="javascript:clickCancelBtn({{reservationInfoId}})">
								<button class="btn"><span>취소</span></button>
							</div>
						</div>
					</div>		
					<div class="right"></div>
				</div>
				<div class="card_footer">
					<div class="left"></div>
					<div class="middle"></div>
					<div class="right"></div>
				</div>
			</a>
			<a class="fn fn-share1 naver-splugin btn_goto_share" title="공유하기"></a>
		</article>
	
		<!-- 취소 팝업 -->
		<!-- [D] 활성화 display:block, 아니오 버튼 or 닫기 버튼 클릭 시 숨김 display:none; -->
		<div class="popup_booking_wrapper" style="display:none;" id="cancel_{{reservationInfoId}}">
			<div class="dimm_dark" style="display:block"></div>
			<div class="popup_booking refund">
				<em class="booking_number">No.{{reservationInfoId}}</em>
				<h1 class="pop_tit">					
					<span>{{displayInfo.productDescription}}</span>
					<small class="sm">{{reservationDate}}</small>
				</h1>
				<div class="nomember_alert">
					<p>취소하시겠습니까?</p>
				</div>
				<div class="pop_bottom_btnarea">
					<div class="btn_gray">
						<a class="btn_bottom" onClick="javascript:exitCancelPopup({{reservationInfoId}})"><span>아니오</span></a>
					</div>
					<div class="btn_green">
						<a class="btn_bottom" onClick="javascript:handleCancel({{reservationInfoId}})"><span>예</span></a>
					</div>
				</div>
				<!-- 닫기 -->
				<a class="popup_btn_close" title="close">
					<i class="spr_book2 ico_cls" onClick="javascript:exitCancelPopup({{reservationInfoId}})"></i>
				</a>
				<!--// 닫기 -->
			</div>
		</div>
		<!--// 취소 팝업 -->
	</script>
	
	<!-- 템플릿 처리를 위한 자바스크립트 Handlebar Library -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.11/handlebars.min.js"></script>   
		
	<!-- js 파일 include -->
	<script type="text/javascript" src="static/script/common/common.js"></script>	
	<script type="text/javascript" src="static/script/myreservation/myreservation.js"></script>
</body>
</html>