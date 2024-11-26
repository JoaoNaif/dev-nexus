package br.com.joaonaif.main.modules.post.useCases;

import br.com.joaonaif.main.exceptions.PostNotFound;
import br.com.joaonaif.main.modules.post.PostEntity;
import br.com.joaonaif.main.modules.post.PostRepository;
import br.com.joaonaif.main.modules.post.dto.EditPostRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EditPostUseCase {

    @Autowired
    private PostRepository postRepository;

    public void execute(UUID postId, EditPostRequestDTO request) {
        var post = this.postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFound());

        post.setTitle(request.getTitle());
        post.setContent(request.getContent());

        this.postRepository.save(post);
    }
}
