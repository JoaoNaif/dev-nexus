package br.com.joaonaif.main.modules.programmer.useCases;

import br.com.joaonaif.main.exceptions.ProgrammerNotFound;
import br.com.joaonaif.main.modules.programmer.ProgrammerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteProgrammerUseCase {

    @Autowired
    private ProgrammerRepository programmerRepository;

    public void execute(UUID programmerId) {
        var programmer = this.programmerRepository.findById(programmerId)
                .orElseThrow(() -> new ProgrammerNotFound());

        this.programmerRepository.delete(programmer);
    }
}
