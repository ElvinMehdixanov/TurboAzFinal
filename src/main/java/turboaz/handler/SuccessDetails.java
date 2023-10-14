package turboaz.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SuccessDetails<T> {

    private T data;

    private int statusCode;

    boolean isSuccess;
}
