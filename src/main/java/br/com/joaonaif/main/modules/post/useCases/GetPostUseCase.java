package br.com.joaonaif.main.modules.post.useCases;

import br.com.joaonaif.main.exceptions.ProgrammerNotFound;
import br.com.joaonaif.main.modules.post.PostRepository;
import br.com.joaonaif.main.modules.post.dto.CreatePostResponseDTO;
import br.com.joaonaif.main.modules.programmer.ProgrammerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GetPostUseCase {

    @Autowired
    private PostRepository postRepository;

    public CreatePostResponseDTO execute(UUID postId) {
        var post = this.postRepository.findById(postId)
                .orElseThrow(() -> new ProgrammerNotFound());

        return CreatePostResponseDTO.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .author(post.getAuthor().getId())
                .likes(post.getLikes().stream()
                        .map(ProgrammerEntity::getUsername)
                        .collect(Collectors.toSet()))
                .build();
    }
}
