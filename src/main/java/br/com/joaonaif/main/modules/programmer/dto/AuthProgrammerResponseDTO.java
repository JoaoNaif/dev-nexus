package br.com.joaonaif.main.modules.programmer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthProgrammerResponseDTO {

    private UUID userId;
    private String access_token;
    private Long expires_in;
}
