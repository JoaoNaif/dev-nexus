package br.com.joaonaif.main.modules.post.useCases;

import br.com.joaonaif.main.exceptions.ProgrammerNotFound;
import br.com.joaonaif.main.modules.post.PostEntity;
import br.com.joaonaif.main.modules.post.PostRepository;
import br.com.joaonaif.main.modules.post.dto.CreatePostResponseDTO;
import br.com.joaonaif.main.modules.programmer.ProgrammerEntity;
import br.com.joaonaif.main.modules.programmer.ProgrammerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GetManyPersonalPostUseCase {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ProgrammerRepository programmerRepository;

    public List<CreatePostResponseDTO> execute(UUID programmerId) {
        var programmer = this.programmerRepository.findById(programmerId)
                .orElseThrow(() -> new ProgrammerNotFound());

        return programmer.getPosts()
                .stream()
                .map(this::convertDTO)
                .toList();
    }

    private CreatePostResponseDTO convertDTO(PostEntity post) {
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
