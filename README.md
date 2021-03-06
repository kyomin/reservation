##  개요   


스프링 프레임워크를 이용하여 온라인 예약 서비스를 구현한 프로젝트입니다.   
프론트 단은 모바일 웹과 PC 웹을 하나의 UI로 구성하여 PC에서도 동작하고, 모바일 웹에서도 동작하도록 하였습니다.   
서버 단은 MVC 디자인 패턴을 이용하여 구현하였고, 데이터베이스는 MySQL을 사용하였습니다.   
다음과 같이 크게 5가지 기능을 개발하였습니다.   


### 1. 메인 페이지

메인 페이지는 GNB, 프로모션, 카테고리, 총 개수표시, 상품리스트, 더 보기 이렇게 6개의 영역으로 구성됩니다.   

‘카테고리’ 영역에서는 화면이 노출될 때 처음엔 ‘전체 리스트’ 항목이 노출됩니다.   
노출되는 아이템 개수는 기본 4개입니다.   
카테고리 내 장르를 선택하면 화면이동 없이 해당 장르 결과를 상품리스트 영역에 노출합니다.   
이는 Ajax를 이용한 비동기 통신을 이용하여 구현하였습니다.   
선택된 장르 메뉴는 녹색으로 하이라이트 됩니다. 상품을 클릭하면 해당 상품의 상세페이지로 이동합니다.

‘더 보기’ 영역을 누르면 추가 데이터를 가져오고 그 결과가 화면에 노출됩니다.   
이는 4개씩 단위로 추가됩니다.   
추가로 보여줄 데이터가 없다면 더 보기 UI는 사라집니다.   
이 또한 Ajax 통신을 이용하였습니다.

‘프로모션’ 영역은 슬라이딩 기능을 사용하여 프로모션 이미지를 순차적으로 노출시킵니다.   
끝까지 가면 다시 되돌아와서 처음 내용이 노출되며, 자동 슬라이딩 되도록 구현하였습니다.


### 2. 상세페이지

메인 페이지에서 선택한 특정 상품의 상세를 보여주는 페이지입니다.   
해당 상품의 id 값을 이용해 서버로부터 상품의 이미지, 타이틀, 상세정보 및 주소, 예매자 후기 및 그 개수, 평점을 불러와 화면에 노출하고, 예매하기 버튼 UI를 구성하였습니다.   
예매하기 버튼을 클릭하면 예약하기 페이지로 넘어갑니다.


### 3. 예약하기

해당 페이지에서는 상품정보, 티켓 수 선택, 예매자 정보, 약관 동의, 예약하기 영역으로 구성됩니다.   

‘티켓 수 선택’ 영역에서 +, - 버튼을 누르면 숫자 및 금액을 변경시키도록 구현하였습니다.   
0명이면 – 버튼이 비활성화됩니다.   

‘예매자 정보’ 영역에서는 예매자로부터 정보를 입력받을 수 있는 양식을 구성하였습니다.   
필수 정보는 꼭 입력될 수 있도록 구현하였습니다.   
모든 정보 입력 후 예약하기 버튼을 누르면 서버로 정보가 전송됩니다.


### 4. 나의 예매 내역 확인

예약확정, 취소한 예약, 이용 완료된 예약 정보를 노출합니다.   
이용 완료된 예약 리스트에 대해서는 예매자 리뷰 남기기 버튼을 노출합니다.


### 5. 한 줄 평 등록

해당 페이지에서는 별점, 리뷰 입력, 사진, 리뷰 등록 영역으로 구성하였습니다.   
‘별점’ 영역에서는 1~5점의 점수를 선택할 수 있게 하였습니다.   
해당 값만큼 별 모양의 UI를 하이라이트 시킬 수 있게 구현하였습니다.   
‘리뷰 입력’ 영역에서는 400자까지의 텍스트를 입력받고, ‘사진’ 영역에서는 사진 파일을 등록할 수 있게 하였습니다.   
이렇게 사용자로부터 입력받은 정보를 form 데이터에 담아 ‘리뷰 등록’ 버튼을 누르면 서버로 전송하게 되고, ‘나의 예매 내역 확인’ 페이지로 이동할 수 있게 구현하였습니다.

## 프로젝트의 디렉토리 구조

`/src/main/webapp`                        => WEB-INF와 웹 관련 리소스들이 위치   
`/src/main/webapp/static`                 => css파일, js(자바스크립트)파일, img(이미지)파일 등이 위치   
`/src/main/webapp/WEB-INF`                => jsp(뷰)파일, 메이븐 web mvc를 위한 xml 설정 파일 위치      


`/src/main/resources`                        => *.properties, *.xml 등 설정 파일 위치   
`/src/main/resources/log`                    => 로깅 관련 설정 파일 위치   
`/src/main/resources/mappers`                => 마이바티스의 쿼리문 설정 파일 위치.   
해당 쿼리문을 정의하여 DAO에서 손 쉽게 사용 가능   
`/src/main/resources/mybatis`                => 마이바티스 설정 파일 위치      


`/src/main/java`                                      => 자바 패키지 폴더와 소스 코드가 위치   
`/src/main/java/kr/or/connect/reservation/config`     => 애플리케이션, 데이터베이스, Web MVC 설정 파일이 위치   
`/src/main/java/kr/or/connect/reservation/controller` => MVC 패턴의 컨트롤러들 위치   
`/src/main/java/kr/or/connect/reservation/service`    => MVC 패턴의 서비스들 위치   
`/src/main/java/kr/or/connect/reservation/dao`        => Data Access Object들이 위치   
`/src/main/java/kr/or/connect/reservation/dto`        => Data Transfer Object들이 위치
