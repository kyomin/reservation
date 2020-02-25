package kr.or.connect.reservation.enums;

import java.util.logging.Level;

public enum ExceptionLevelLog {
	SQL_EXCEPTION(Level.WARNING),
	NO_SUCH_ELEMENT_EXCEPTION(Level.WARNING),
	BAD_REQUEST_EXCEPTION(Level.WARNING),
	ClASS_NOT_FOUND_EXCEPTION(Level.SEVERE),
	IO_EXCEPTION(Level.SEVERE),
	NOT_VALID_EXCEPTION(Level.WARNING),
	ETC_EXCEPTION(Level.SEVERE);

	private Level level;

	private ExceptionLevelLog(Level level) {
		this.level = level;
	}

	public Level getLevel() {
		return level;
	}
}