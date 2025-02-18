package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
@Tag(name = "Комментарии")
public class CommentController {

    @GetMapping("/{id}/comments")
    @Operation(summary = "Получить комментарии")
    public CommentsDTO getComments(@PathVariable Integer id) {
        return new CommentsDTO();
    }

    @PostMapping("/{id}/comments")
    @Operation(summary = "Добавить комментарий")
    public CommentDTO addComment(
            @PathVariable Integer id,
            @RequestBody CreateOrUpdateCommentDTO comment
    ) {
        return new CommentDTO();
    }

    @DeleteMapping("/{adId}/comments/{commentId}")
    @Operation(summary = "Удалить комментарий")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Integer adId,
            @PathVariable Integer commentId
    ) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{adId}/comments/{commentId}")
    @Operation(summary = "Обновить комментарий")
    public CommentDTO updateComment(
            @PathVariable Integer adId,
            @PathVariable Integer commentId,
            @RequestBody CreateOrUpdateCommentDTO comment
    ) {
        return new CommentDTO();
    }
}
