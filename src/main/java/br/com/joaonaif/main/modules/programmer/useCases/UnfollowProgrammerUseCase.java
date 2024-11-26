package br.com.joaonaif.main.modules.programmer.useCases;

import br.com.joaonaif.main.exceptions.ProgrammerNotFound;
import br.com.joaonaif.main.modules.programmer.ProgrammerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UnfollowProgrammerUseCase {

    @Autowired
    private ProgrammerRepository programmerRepository;

    public void execute(UUID unfollowerId, UUID unfollowedId) {
        var unfollower = this.programmerRepository.findById(unfollowerId)
                .orElseThrow(() -> {
                    throw new ProgrammerNotFound();
                });

        var unfollowed = this.programmerRepository.findById(unfollowedId)
                .orElseThrow(() -> {
                    throw new ProgrammerNotFound();
                });

        if (unfollower.getFollowing().contains(unfollowed)) {
            unfollower.getFollowing().remove(unfollowed);
        }

        if (unfollowed.getFollowers().contains(unfollower)) {
            unfollowed.getFollowers().remove(unfollower);
        }

        programmerRepository.save(unfollower);
        programmerRepository.save(unfollowed);
    }
}
