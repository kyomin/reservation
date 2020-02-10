package kr.or.connect.reservation.dao.detail;

/*
 * 		detail 페이지 내에서 빈번하게 작업하는 쿼리를 모아놓은 클래스이다.
 */
public class CommonDaoSqls {
	public static final String SELECT_PRODUCT_ID_WITH_DISPLAY_INFO_ID = "SELECT product_id AS productId FROM display_info WHERE id = :displayInfoId";
}
