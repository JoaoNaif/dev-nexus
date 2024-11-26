package br.com.joaonaif.main.modules.programmer.useCases;

import br.com.joaonaif.main.exceptions.ProgrammerAlreadyExist;
import br.com.joaonaif.main.modules.programmer.ProgrammerEntity;
import br.com.joaonaif.main.modules.programmer.ProgrammerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateProgrammerUseCase {

    @Autowired
    private ProgrammerRepository programmerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public ProgrammerEntity execute(ProgrammerEntity programmerEntity) {
        this.programmerRepository.findByUsernameOrEmail(programmerEntity.getUsername(), programmerEntity.getEmail())
                .ifPresent((user) -> {
                    throw new ProgrammerAlreadyExist();
                });

        var password = passwordEncoder.encode(programmerEntity.getPassword());
        programmerEntity.setPassword(password);

        return this.programmerRepository.save(programmerEntity);
    }
}
