package turboaz.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Email;

@Data
@ToString
@Builder
public class SearchTextChangeDto {
    @Email
    private String mail;
    private String url;
}
