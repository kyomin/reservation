package kr.or.connect.reservation.dao.detail;

public class DisplayInfoImageDaoSqls {
	public static final String SELECT_DISPLAY_INFO_IMAGE = "SELECT content_type AS contentType, create_date AS createDate, delete_flag AS deleteFlag, display_info_image.display_info_id AS displayInfoId, display_info_image.id AS displayInfoImageId, file_id AS fileId, file_name AS fileName, modify_date AS modifyDate, save_file_name AS saveFileName	FROM display_info_image LEFT JOIN file_info ON display_info_image.file_id = file_info.id WHERE display_info_image.display_info_id = :displayInfoId";
}
