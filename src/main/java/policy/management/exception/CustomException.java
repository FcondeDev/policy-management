package policy.management.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import policy.management.exception.error.ErrorEnum;

@Getter
public class CustomException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final ErrorEnum errorEnum;

    public CustomException(ErrorEnum errorEnum, HttpStatus httpStatus) {
        this.errorEnum = errorEnum;
        this.httpStatus = httpStatus;

    }

}

