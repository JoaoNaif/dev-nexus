package br.com.joaonaif.main.modules.post.useCases;

import br.com.joaonaif.main.modules.post.PostEntity;
import br.com.joaonaif.main.modules.post.PostRepository;
import br.com.joaonaif.main.modules.post.dto.CreatePostResponseDTO;
import br.com.joaonaif.main.modules.programmer.ProgrammerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetManyAllPostUseCase {

    @Autowired
    private PostRepository postRepository;

    public List<CreatePostResponseDTO> execute(String filter) {
        var posts = this.postRepository.findManyByTitleContaining(filter);

        return posts.stream()
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
