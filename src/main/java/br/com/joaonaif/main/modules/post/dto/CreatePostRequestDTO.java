package br.com.joaonaif.main.modules.post.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CreatePostRequestDTO {

    private String title;
    private String content;
    private UUID authorId;
}
