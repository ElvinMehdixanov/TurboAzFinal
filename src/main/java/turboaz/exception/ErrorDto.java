package turboaz.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDto {
    ErrorResponse errorResponse;
    private int statusCode;
    private boolean success;

}
