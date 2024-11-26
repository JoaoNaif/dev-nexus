package br.com.joaonaif.main.modules.programmer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetManyFollowingResponseDTO {

    private UUID id;
    private String username;
    private String email;
}
