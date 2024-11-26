package br.com.joaonaif.main.modules.programmer.useCases;

import br.com.joaonaif.main.modules.programmer.ProgrammerRepository;
import br.com.joaonaif.main.modules.programmer.dto.AuthProgrammerRequestDTO;
import br.com.joaonaif.main.modules.programmer.dto.AuthProgrammerResponseDTO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthProgrammerUseCase {

    @Value("${security.token.secret.programmer}")
    private String secretKey;

    @Autowired
    private ProgrammerRepository programmerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthProgrammerResponseDTO execute(AuthProgrammerRequestDTO authProgrammerRequestDTO) throws AuthenticationException {
        var programmer = this.programmerRepository.findByUsername(authProgrammerRequestDTO.username())
                .orElseThrow(() -> new UsernameNotFoundException("Username/password incorrect"));

        var passwordMatches = this.passwordEncoder.matches(authProgrammerRequestDTO.password(), programmer.getPassword());

        if (!passwordMatches) {
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var expiresIn = Instant.now().plus(Duration.ofMinutes(60));
        var token = JWT.create()
                .withIssuer("nexus")
                .withSubject(programmer.getId().toString())
                .withClaim("roles", Arrays.asList("PROGRAMMER"))
                .withExpiresAt(expiresIn)
                .sign(algorithm);

        var authProgrammerResponseDTO = AuthProgrammerResponseDTO.builder()
                .userId(programmer.getId())
                .access_token(token)
                .expires_in(expiresIn.toEpochMilli())
                .build();

        return authProgrammerResponseDTO;
    }
}
