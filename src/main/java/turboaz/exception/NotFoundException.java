package turboaz.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NotFoundException extends  RuntimeException{
    public  NotFoundException(String msj){
        super(msj);
    }
}
