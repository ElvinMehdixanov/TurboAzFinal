package turboaz.controller.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import turboaz.dto.security.AfterSignInResponseDto;
import turboaz.dto.security.SignUpDto;
import turboaz.entity.UserEntity;
import turboaz.model.JwtRequest;
import turboaz.repository.UserRepo;
import turboaz.securityconfig.JwtTokenUtil;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class JwtAuthenticationController {

    private final UserDetailsService jwtInMemoryUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public AfterSignInResponseDto signIn(@RequestBody JwtRequest request)
            throws Exception {

        authenticate(request.getEmail(), request.getPassword());
        final UserDetails userDetails = jwtInMemoryUserDetailsService
                .loadUserByUsername(request.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);
        AfterSignInResponseDto signInResponseDto = AfterSignInResponseDto.builder()
                .token(token)
                .email(request.getEmail())
                .password(request.getPassword())
                .role(request.getRole())
                .build();

        return signInResponseDto;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpDto dto) throws MethodArgumentNotValidException {

        try {
            UserEntity entity = userRepo.findUsersEntityByEmail(dto.getEmail());
            if (entity == null) {
                UserEntity userEntity = UserEntity.builder()
                        .email(dto.getEmail())
                        .password(passwordEncoder.encode(dto.getPassword()))
                        .role(dto.getRole())
                        .build();
                userRepo.save(userEntity);
                return ResponseEntity.ok("You signed!");
            } else
                return ResponseEntity.ok("This account already exist in our DB!");
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());

        }
    }


    private void authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
