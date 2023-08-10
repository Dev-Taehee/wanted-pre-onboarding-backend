package wanted.preonboardingbackend.exception;

import lombok.Getter;

public enum ExceptionCode {

    MEMBER_EXISTS(409, "Member exists"),
    MEMBER_INVALID(404, "INVALID MEMBER");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }

}
