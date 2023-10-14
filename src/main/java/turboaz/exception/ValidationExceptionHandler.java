package turboaz.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ValidationExceptionHandler {
     @ExceptionHandler
             (MethodArgumentNotValidException.class)
        public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
            Map<String, String> errors = new HashMap<>();

            BindingResult bindingResult = ex.getBindingResult();

            bindingResult.getAllErrors().forEach((error) -> {
                if (error instanceof FieldError) {
                    FieldError fieldError = (FieldError) error;
                    errors.put(fieldError.getField(), error.getDefaultMessage());
                }
            });

            return ResponseEntity.badRequest().body(errors);
        }
    }

