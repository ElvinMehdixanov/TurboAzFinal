package turboaz.dto.security;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class SignUpDto {

    @Email(message = "Zehmet olmasa duzgun email daxil edin ")
    private String email;

    @Size(min = 8, message = "parolun uzunluqu min 8 simvol olmalidir")
    private String password;

    private String role;


    public SignUpDto(String email, String password) {
        this.email = email;
        this.password = password;

    }

}
