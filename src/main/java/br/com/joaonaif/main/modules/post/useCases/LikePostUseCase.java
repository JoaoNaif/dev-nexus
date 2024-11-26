package br.com.joaonaif.main.modules.post.useCases;

import br.com.joaonaif.main.exceptions.PostNotFound;
import br.com.joaonaif.main.exceptions.ProgrammerNotFound;
import br.com.joaonaif.main.modules.post.PostRepository;
import br.com.joaonaif.main.modules.programmer.ProgrammerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LikePostUseCase {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ProgrammerRepository programmerRepository;

    public void execute(UUID programmerId, UUID postId) {
        var programmer = this.programmerRepository.findById(programmerId)
                .orElseThrow(() -> new ProgrammerNotFound());

        var post = this.postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFound());

        if (!post.getLikes().contains(programmer)) {
            post.getLikes().add(programmer);
        } else {
            post.getLikes().remove(programmer);
        }

        this.postRepository.save(post);
    }
}
