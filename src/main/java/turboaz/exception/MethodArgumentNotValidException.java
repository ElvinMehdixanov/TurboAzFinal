package turboaz.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MethodArgumentNotValidException extends RuntimeException{
    public MethodArgumentNotValidException(String msj){
        super(msj);
    }
}
