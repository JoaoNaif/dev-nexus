package br.com.joaonaif.main.modules.programmer.useCases;

import br.com.joaonaif.main.exceptions.ProgrammerNotFound;
import br.com.joaonaif.main.modules.programmer.ProgrammerEntity;
import br.com.joaonaif.main.modules.programmer.ProgrammerRepository;
import br.com.joaonaif.main.modules.programmer.dto.GetManyFollowingResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GetManyFollowingUseCase {

    @Autowired
    private ProgrammerRepository programmerRepository;

    public List<GetManyFollowingResponseDTO> execute(UUID programmerId) {
        var programmer = this.programmerRepository.findById(programmerId)
                .orElseThrow(() -> new ProgrammerNotFound());

        return programmer.getFollowing()
                .stream()
                .map(this::convertDTO)
                .toList();
    }

    private GetManyFollowingResponseDTO convertDTO(ProgrammerEntity following) {
        return GetManyFollowingResponseDTO.builder()
                .id(following.getId())
                .username(following.getUsername())
                .email(following.getEmail())
                .build();
    }
}
