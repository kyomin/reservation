package kr.or.connect.reservation.dao.detail;

public class ProductImageDaoSqls {
	public static final String SELECT_ALL_PRODUCT_IMAGES = "SELECT content_type AS contentType, create_date AS createDate, delete_flag AS deleteFlag, file_info.id AS fileInfoId, file_name AS fileName, modify_date AS modifyDate, product_id AS productId, product_image.id AS productImageId, save_file_name AS saveFileName, type FROM product_image INNER JOIN file_info ON product_image.file_id = file_info.id WHERE product_image.product_id = :productId";
}