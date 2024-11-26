package br.com.joaonaif.main.modules.programmer.controller;

import br.com.joaonaif.main.modules.programmer.dto.AuthProgrammerRequestDTO;
import br.com.joaonaif.main.modules.programmer.useCases.AuthProgrammerUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/programmer")
public class AuthProgrammerController {

    @Autowired
    private AuthProgrammerUseCase authProgrammerUseCase;

    @PostMapping("/auth")
    public ResponseEntity<Object> auth(@RequestBody AuthProgrammerRequestDTO authProgrammerRequestDTO) {
        try {
            var token = this.authProgrammerUseCase.execute(authProgrammerRequestDTO);
            return ResponseEntity.ok().body(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
