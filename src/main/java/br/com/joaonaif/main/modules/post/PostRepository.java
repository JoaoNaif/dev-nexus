package br.com.joaonaif.main.modules.post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostRepository extends JpaRepository<PostEntity, UUID> {
    Optional<PostEntity> findByTitle(String title);
    List<PostEntity> findManyByTitleContaining(String filter);
}
