package policy.management.exception;

import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import policy.management.dto.ApiErrorDTO;
import policy.management.exception.error.ErrorEnum;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.logging.Level;

@RestControllerAdvice
@Log
public class ErrorHandler {


    @ResponseBody
    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<ApiErrorDTO> handleCustomException(CustomException exception) {
        log.log(Level.SEVERE, String.format("---  Custom Exception Thrown : %s ---", exception.getErrorEnum().description));
        return new ResponseEntity<>(new ApiErrorDTO(exception.getErrorEnum().code, exception.getErrorEnum().title, exception.getErrorEnum().description), HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ApiErrorDTO> handleNotFoundException(NotFoundException exception) {
        log.log(Level.SEVERE, "--- Resource Not Found -----");
        return new ResponseEntity<>(new ApiErrorDTO(ErrorEnum.NOT_FOUND_EXCEPTION.code, ErrorEnum.NOT_FOUND_EXCEPTION.title, ErrorEnum.NOT_FOUND_EXCEPTION.description), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    public ResponseEntity<ApiErrorDTO> handleConstraintValidationException(ConstraintViolationException constraintViolationException) {
        StringBuilder violations = new StringBuilder();
        for (ConstraintViolation violation : constraintViolationException.getConstraintViolations()) {
            violations.append("property: ").append(violation.getPropertyPath().toString()).append(", error: ").append(violation.getMessage()).append(". ");
        }
        log.log(Level.SEVERE, String.format("---Constraint violated %s -----", violations));
        return new ResponseEntity<>(new ApiErrorDTO(ErrorEnum.CONSTRAINT_VALIDATION_EXCEPTION.code, ErrorEnum.CONSTRAINT_VALIDATION_EXCEPTION.title, violations.toString()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ApiErrorDTO> HandleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        StringBuilder violations = new StringBuilder();
        for (FieldError fieldError : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
            violations.append("property: ").append(fieldError.getField()).append(", error: ").append(fieldError.getDefaultMessage()).append(". ");
        }
        log.log(Level.SEVERE, String.format("---Constraint violated %s -----", violations));
        return new ResponseEntity<>(new ApiErrorDTO(ErrorEnum.CONSTRAINT_VALIDATION_EXCEPTION.code, ErrorEnum.CONSTRAINT_VALIDATION_EXCEPTION.title, violations.toString()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity<ApiErrorDTO> unexpectedException(Exception exception) {
        log.log(Level.SEVERE,"---Unexpected exception occurred -----", exception);
        return new ResponseEntity<>(new ApiErrorDTO(ErrorEnum.UNEXPECTED_EXCEPTION.code, ErrorEnum.UNEXPECTED_EXCEPTION.title, ErrorEnum.UNEXPECTED_EXCEPTION.description), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
