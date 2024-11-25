package Job.exception;

public enum ServiceErrorCode {
    ADMIN_ALREADY_EXISTS("ADMIN_ALREADY_EXISTS"),
    USER_ALREADY_EXISTS("USER_ALREADY_EXISTS"),
    BOARDCATEGORY_ALREADY_EXISTS("BOARDCATEGORY_ALREADY_EXISTS"),
    ADMIN_NOT_FOUND("ADMIN_NOT_FOUND"),
    USER_NOT_FOUND("USER_NOT_FOUND"),
    BOARD_NOT_FOUND("BOARD_NOT_FOUND"),
    BOARDCATEGORY_NOT_FOUND("BOARDCATEGORY_NOT_FOUND"),
    INVALID_LOGIN("INVALID_LOGIN"),
    DATABASE_ERROR("DATABASE_ERROR");

    private final String code;

    ServiceErrorCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
