package br.com.joaonaif.main.modules.post.useCases;

import br.com.joaonaif.main.exceptions.PostNotFound;
import br.com.joaonaif.main.modules.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeletePostUseCase {

    @Autowired
    private PostRepository postRepository;

    public void execute(UUID postId) {
        var post = this.postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFound());

        this.postRepository.delete(post);
    }
}
