package br.com.joaonaif.main.modules.programmer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProgrammerRepository extends JpaRepository<ProgrammerEntity, UUID> {
    Optional<ProgrammerEntity> findByUsernameOrEmail(String username, String email);
    Optional<ProgrammerEntity> findByUsername(String username);
    List<ProgrammerEntity> findManyByUsernameContaining(String filter);
}
