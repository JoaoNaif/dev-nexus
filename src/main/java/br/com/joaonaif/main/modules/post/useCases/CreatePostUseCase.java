package br.com.joaonaif.main.modules.post.useCases;

import br.com.joaonaif.main.exceptions.ProgrammerNotFound;
import br.com.joaonaif.main.modules.post.PostEntity;
import br.com.joaonaif.main.modules.post.PostRepository;
import br.com.joaonaif.main.modules.post.dto.CreatePostRequestDTO;
import br.com.joaonaif.main.modules.post.dto.CreatePostResponseDTO;
import br.com.joaonaif.main.modules.programmer.ProgrammerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreatePostUseCase {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ProgrammerRepository programmerRepository;

    public CreatePostResponseDTO execute(CreatePostRequestDTO request) {
        var author = this.programmerRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new ProgrammerNotFound());


        PostEntity post = new PostEntity();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setAuthor(author);

        this.postRepository.save(post);

        return CreatePostResponseDTO.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .author(author.getId())
                .build();
    }
}
