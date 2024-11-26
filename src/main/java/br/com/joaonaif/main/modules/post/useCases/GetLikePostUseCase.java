package br.com.joaonaif.main.modules.post.useCases;

import br.com.joaonaif.main.exceptions.PostNotFound;
import br.com.joaonaif.main.modules.post.PostRepository;
import br.com.joaonaif.main.modules.post.dto.GetLikePostResponseDTO;
import br.com.joaonaif.main.modules.programmer.ProgrammerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GetLikePostUseCase {

    @Autowired
    private PostRepository postRepository;

    public List<GetLikePostResponseDTO> execute(UUID postId) {
        var post = this.postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFound());

        return post.getLikes()
                .stream()
                .map(this::convertDTO)
                .toList();
    }

    private GetLikePostResponseDTO convertDTO(ProgrammerEntity programmer) {
        return GetLikePostResponseDTO.builder()
                .id(programmer.getId())
                .username(programmer.getUsername())
                .email(programmer.getEmail())
                .build();
    }
}
