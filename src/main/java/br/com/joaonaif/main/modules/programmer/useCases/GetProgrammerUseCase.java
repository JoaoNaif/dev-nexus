package br.com.joaonaif.main.modules.programmer.useCases;

import br.com.joaonaif.main.exceptions.ProgrammerNotFound;
import br.com.joaonaif.main.modules.post.PostEntity;
import br.com.joaonaif.main.modules.programmer.ProgrammerEntity;
import br.com.joaonaif.main.modules.programmer.ProgrammerRepository;
import br.com.joaonaif.main.modules.programmer.dto.GetProgrammerResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GetProgrammerUseCase {

    @Autowired
    private ProgrammerRepository programmerRepository;

    public GetProgrammerResponseDTO execute(UUID programmerId) {
        var programmer = this.programmerRepository.findById(programmerId)
                .orElseThrow(() -> {
                    throw new ProgrammerNotFound();
                });

        List<String> followers = programmer.getFollowers().stream()
                .map(ProgrammerEntity::getUsername)
                .collect(Collectors.toList());

        List<String> following = programmer.getFollowing().stream()
                .map(ProgrammerEntity::getUsername)
                .collect(Collectors.toList());


        return GetProgrammerResponseDTO.builder()
                .id(programmer.getId())
                .username(programmer.getUsername())
                .email(programmer.getEmail())
                .createdAt(programmer.getCreatedAt())
                .followers(programmer.getFollowers().stream()
                        .map(ProgrammerEntity::getUsername)
                        .collect(Collectors.toSet()))
                .following(programmer.getFollowing().stream()
                        .map(ProgrammerEntity::getUsername)
                        .collect(Collectors.toSet()))
                .posts(programmer.getPosts().stream()
                        .map(PostEntity::getId)
                        .collect(Collectors.toSet()))
                .build();
    }
}
