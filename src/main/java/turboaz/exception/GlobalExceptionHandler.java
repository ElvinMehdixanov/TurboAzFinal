package turboaz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto>handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        String[] message = {e.getMessage()};

        ErrorResponse errorResponse = new ErrorResponse(message, true);
        ErrorDto errorDto = new ErrorDto(errorResponse, HttpStatus.BAD_REQUEST.value(),false);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDto>handleNotFoundException(NotFoundException e){
        String[] message = {e.getMessage()};

        ErrorResponse errorResponse = new ErrorResponse(message, true);
        ErrorDto errorDto = new ErrorDto(errorResponse, HttpStatus.NOT_FOUND.value(),false);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }


}
