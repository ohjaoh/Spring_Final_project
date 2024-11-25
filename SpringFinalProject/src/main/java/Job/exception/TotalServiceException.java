package Job.exception;

public class TotalServiceException extends RuntimeException {
    private final ServiceErrorCode errorCode; // 예외 구분을 위한 코드

    public TotalServiceException(String message, ServiceErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public TotalServiceException(String message, ServiceErrorCode errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public ServiceErrorCode getErrorCode() {
        return errorCode;
    }
}
