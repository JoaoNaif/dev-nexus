package br.com.joaonaif.main.modules.programmer.useCases;

import br.com.joaonaif.main.exceptions.ProgrammerNotFound;
import br.com.joaonaif.main.modules.programmer.ProgrammerEntity;
import br.com.joaonaif.main.modules.programmer.ProgrammerRepository;
import br.com.joaonaif.main.modules.programmer.dto.GetManyFollowersResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GetManyFollowersUseCase {

    @Autowired
    private ProgrammerRepository programmerRepository;

    public List<GetManyFollowersResponseDTO> execute(UUID programmerId) {
        var programmer = this.programmerRepository.findById(programmerId)
                .orElseThrow(() -> new ProgrammerNotFound());

        return programmer.getFollowers()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    private GetManyFollowersResponseDTO convertToDTO(ProgrammerEntity follower) {
        return GetManyFollowersResponseDTO.builder()
                .id(follower.getId())
                .username(follower.getUsername())
                .email(follower.getEmail())
                .build();
    }
}
