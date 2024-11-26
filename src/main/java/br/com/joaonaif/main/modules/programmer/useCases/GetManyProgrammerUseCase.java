package br.com.joaonaif.main.modules.programmer.useCases;

import br.com.joaonaif.main.modules.post.PostEntity;
import br.com.joaonaif.main.modules.programmer.ProgrammerEntity;
import br.com.joaonaif.main.modules.programmer.ProgrammerRepository;
import br.com.joaonaif.main.modules.programmer.dto.GetProgrammerResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetManyProgrammerUseCase {

    @Autowired
    private ProgrammerRepository programmerRepository;

    public List<GetProgrammerResponseDTO> execute(String filter) {
        var programmers = this.programmerRepository.findManyByUsernameContaining(filter);

        return programmers.stream()
                .map(this::convertDTO)
                .toList();
    }

    private GetProgrammerResponseDTO convertDTO(ProgrammerEntity programmer) {
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
