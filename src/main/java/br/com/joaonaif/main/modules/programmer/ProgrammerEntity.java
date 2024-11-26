package br.com.joaonaif.main.modules.programmer;

import br.com.joaonaif.main.modules.post.PostEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.*;

@Data
@Entity(name = "programmer")
public class ProgrammerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank()
    @Pattern(regexp = "\\S+", message = "O campo não pode conter espaço")
    private String username;

    @Email(message = "E-mail inválido")
    private String email;

    @Length(min = 6, max = 100)
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostEntity> posts = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "programmer_followers",
            joinColumns = @JoinColumn(name = "followed_id"),
            inverseJoinColumns = @JoinColumn(name = "follower_id")
    )
    @JsonIgnore
    private List<ProgrammerEntity> followers = new ArrayList<>();

    @ManyToMany(mappedBy = "followers")
    @JsonIgnore
    private List<ProgrammerEntity> following = new ArrayList<>();

}
