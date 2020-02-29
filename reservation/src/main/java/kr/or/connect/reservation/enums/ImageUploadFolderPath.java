package kr.or.connect.reservation.enums;

public enum ImageUploadFolderPath {
	POSTER("static/img/poster/"),
	MAP("static/img/map/"),
	COMMENT("static/img/comment/");

	private String folderPath;

	private ImageUploadFolderPath(String folderPath) {
		this.folderPath = folderPath;
	}

	public String getFolderPath() {
		return folderPath;
	}
}
