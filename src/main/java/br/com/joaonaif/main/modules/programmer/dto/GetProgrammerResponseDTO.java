package br.com.joaonaif.main.modules.programmer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetProgrammerResponseDTO {

    private UUID id;

    private String username;
    private String email;
    private LocalDateTime createdAt;

    private Set<String> followers;
    private Set<String> following;

    private Set<UUID> posts;
}
