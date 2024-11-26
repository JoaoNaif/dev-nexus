package br.com.joaonaif.main.modules.post.controller;

import br.com.joaonaif.main.modules.post.PostEntity;
import br.com.joaonaif.main.modules.post.dto.CreatePostRequestDTO;
import br.com.joaonaif.main.modules.post.dto.CreatePostResponseDTO;
import br.com.joaonaif.main.modules.post.dto.EditPostRequestDTO;
import br.com.joaonaif.main.modules.post.useCases.*;
import br.com.joaonaif.main.modules.programmer.dto.GetProgrammerResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private CreatePostUseCase createPostUseCase;

    @Autowired
    private EditPostUseCase editPostUseCase;

    @Autowired
    private GetPostUseCase getPostUseCase;

    @Autowired
    private GetManyPersonalPostUseCase getManyPersonalPostUseCase;

    @Autowired
    private GetManyAllPostUseCase getManyAllPostUseCase;

    @Autowired
    private DeletePostUseCase deletePostUseCase;

    @Autowired
    private LikePostUseCase likePostUseCase;

    @Autowired
    private GetLikePostUseCase getLikePostUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid  @RequestBody CreatePostRequestDTO request) {
        try {
            var result = this.createPostUseCase.execute(request);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Object> get(@PathVariable UUID postId) {
        try {
             var result = this.getPostUseCase.execute(postId);
             return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/personal/{programmerId}")
    public ResponseEntity<Object> getManyPersonal(@PathVariable UUID programmerId) {
        try {
            var result = this.getManyPersonalPostUseCase.execute(programmerId);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/search")
    public List<CreatePostResponseDTO> GetManyPostByFilter(@RequestParam String filter) {
        return this.getManyAllPostUseCase.execute(filter);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Object> put(@PathVariable UUID postId, @Valid @RequestBody EditPostRequestDTO request) {
        try {
            this.editPostUseCase.execute(postId, request);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Object> delete(@PathVariable UUID postId) {
        try {
            this.deletePostUseCase.execute(postId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{programmerId}/like/{postId}")
    public ResponseEntity<Object> like(@PathVariable UUID programmerId, @PathVariable UUID postId) {
        try {
            this.likePostUseCase.execute(programmerId, postId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/like/{postId}/all")
    public ResponseEntity<Object> likeAll(@PathVariable UUID postId) {
        try {
            var result = this.getLikePostUseCase.execute(postId);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
