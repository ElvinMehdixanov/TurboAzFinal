package turboaz.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class SearchCarDto {
    private String mail;
    private String url;
}
