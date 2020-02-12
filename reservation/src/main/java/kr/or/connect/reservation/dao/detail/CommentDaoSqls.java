package kr.or.connect.reservation.dao.detail;

public class CommentDaoSqls {
	// select comment 
	public static final String SELECT_COMMENT_BY_PRODUCT_ID = "(SELECT * FROM reservation_user_comment WHERE product_id = :productId)";
	public static final String SELECT_ALL_COMMENTS_BY_PRODUCT_ID_WITHOUT_COMMENT_IMAGE = "SELECT comment, reservation_user_comment_by_product_id.id AS commentId, date_format(reservation_user_comment_by_product_id.create_date, '%Y.%m.%d') AS createDate, date_format(reservation_user_comment_by_product_id.modify_date, '%Y.%m.%d') AS modifyDate, reservation_user_comment_by_product_id.product_id AS productId, date_format(reservation_date, '%Y.%m.%d') AS reservationDate, reservation_email AS reservationEmail, reservation_info.id AS reservationInfoId, reservation_name AS reservationName, reservation_tel AS reservationTelephone, score FROM reservation_info INNER JOIN " + SELECT_COMMENT_BY_PRODUCT_ID + " AS reservation_user_comment_by_product_id ON reservation_info.id = reservation_user_comment_by_product_id.reservation_info_id WHERE reservation_info.product_id = reservation_user_comment_by_product_id.product_id ORDER BY createDate DESC";
	
	// select average score of comment 
	public static final String SELECT_AVERAGE_SCORE_OF_COMMENT_BY_PRODUCT_ID = "SELECT AVG(comment_with_product_id.score) FROM " + SELECT_COMMENT_BY_PRODUCT_ID + " AS comment_with_product_id";
	
	// select comment image by reservation user comment id
	public static final String SELECT_ALL_COMMENT_IMAGES_BY_RESERVATION_USER_COMMENT_ID = "SELECT content_type AS contentType, create_date AS createDate, delete_flag AS deleteFlag, file_id AS fileId, file_name AS fileName, reservation_user_comment_image.id AS imageId, modify_date AS modifyDate, save_file_name AS saveFileName, reservation_info_id AS reservationInfoId, reservation_user_comment_id AS reservationUserCommentId FROM reservation_user_comment_image LEFT JOIN file_info ON reservation_user_comment_image.file_id = file_info.id WHERE reservation_user_comment_id = :reservationUserCommentId";
}