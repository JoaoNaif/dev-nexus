package br.com.joaonaif.main.modules.programmer.controller;

import br.com.joaonaif.main.modules.programmer.ProgrammerEntity;
import br.com.joaonaif.main.modules.programmer.dto.GetProgrammerResponseDTO;
import br.com.joaonaif.main.modules.programmer.useCases.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/programmer")
public class ProgrammerController {

    @Autowired
    private CreateProgrammerUseCase createProgrammerUseCase;

    @Autowired
    private FollowProgrammerUseCase followProgrammerUseCase;

    @Autowired
    private UnfollowProgrammerUseCase unfollowProgrammerUseCase;

    @Autowired
    private GetProgrammerUseCase getProgrammerUseCase;

    @Autowired
    private GetManyFollowersUseCase getManyFollowersUseCase;

    @Autowired
    private GetManyFollowingUseCase getManyFollowingUseCase;

    @Autowired
    private GetManyProgrammerUseCase getManyProgrammerUseCase;

    @Autowired
    private DeleteProgrammerUseCase deleteProgrammerUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody ProgrammerEntity programmerEntity) {
        try {
            var result = this.createProgrammerUseCase.execute(programmerEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{programmerId}")
    public ResponseEntity<Object> get(@PathVariable UUID programmerId) {
        try {
            var result = this.getProgrammerUseCase.execute(programmerId);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("/{followerId}/follow/{followedId}")
    public ResponseEntity<Object> follow(@PathVariable UUID followerId, @PathVariable UUID followedId) {
        try {
            this.followProgrammerUseCase.execute(followerId, followedId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{unfollowerId}/unfollow/{unfollowedId}")
    public ResponseEntity<Object> unfollow(@PathVariable UUID unfollowerId, @PathVariable UUID unfollowedId) {
        try {
            this.unfollowProgrammerUseCase.execute(unfollowerId, unfollowedId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/follower/{programmerId}")
    public ResponseEntity<Object> getManyFollower(@PathVariable UUID programmerId) {
        try {
            var result = this.getManyFollowersUseCase.execute(programmerId);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/following/{programmerId}")
    public ResponseEntity<Object> getManyFollowing(@PathVariable UUID programmerId) {
        try {
            var result = this.getManyFollowingUseCase.execute(programmerId);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/search")
    public List<GetProgrammerResponseDTO> GetManyProgrammerByFilter(@RequestParam String filter) {
        return this.getManyProgrammerUseCase.execute(filter);
    }

    @DeleteMapping("/{programmerId}")
    public ResponseEntity<Object> delete(@PathVariable UUID programmerId) {
        try {
            this.deleteProgrammerUseCase.execute(programmerId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
