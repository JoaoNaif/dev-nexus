package br.com.joaonaif.main.modules.programmer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthProgrammerResponseDTO {

    private String access_token;
    private Long expires_in;
}
