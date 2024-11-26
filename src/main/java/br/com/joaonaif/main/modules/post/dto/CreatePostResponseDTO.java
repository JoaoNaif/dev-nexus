package br.com.joaonaif.main.modules.post.dto;

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
public class CreatePostResponseDTO {

    private UUID id;

    private String title;
    private String content;
    private LocalDateTime createdAt;

    private UUID author;

    private Set<String> likes;
}
