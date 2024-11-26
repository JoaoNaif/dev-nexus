package br.com.joaonaif.main.modules.programmer.useCases;

import br.com.joaonaif.main.exceptions.ProgrammerNotFound;
import br.com.joaonaif.main.modules.programmer.ProgrammerEntity;
import br.com.joaonaif.main.modules.programmer.ProgrammerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FollowProgrammerUseCase {

    @Autowired
    private ProgrammerRepository programmerRepository;

    public void execute(UUID followerId, UUID followedId) {
        var follower = this.programmerRepository.findById(followerId)
                .orElseThrow(() -> {
                    throw new ProgrammerNotFound();
                });

        var followed = this.programmerRepository.findById(followedId)
                .orElseThrow(() -> {
                    throw new ProgrammerNotFound();
                });

        // Verifica se já está na lista de "following"
        if (!follower.getFollowing().contains(followed)) {
            follower.getFollowing().add(followed);
        }

        // Verifica se já está na lista de "followers"
        if (!followed.getFollowers().contains(follower)) {
            followed.getFollowers().add(follower);
        }

        programmerRepository.save(follower);
        programmerRepository.save(followed);
    }
}
