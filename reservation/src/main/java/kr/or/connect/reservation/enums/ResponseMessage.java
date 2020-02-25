package kr.or.connect.reservation.enums;

public enum ResponseMessage {
	INTERNAL_SERVER_ERROR("서버 내부 에러가 발생했습니다."),
	DB_ERROR("데이터베이스 에러가 발생했습니다."),
	BAD_REQUEST("올바르지 않은 요청입니다."),
	NOT_FOUND("Mapping되는 요청이 아닙니다. API 문서를 확인해 주세요."),
	NO_SUCH_ELEMENT("요청하신 조건에 맞는 데이터가 없습니다."),
	IO_EXCEPTION("파일 upload 또는 download 중 문제가 발생했습니다."),
	SUCCESS("성공적으로 요청이 완료됐습니다.");

	private String message;

	private ResponseMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}