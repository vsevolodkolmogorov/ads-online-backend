package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
@Tag(name = "Комментарии")
public class CommentController {

    @GetMapping("/{id}/comments")
    @Operation(summary = "Получить комментарии")
    public Comments getComments(@PathVariable Integer id) {
        return new Comments();
    }

    @PostMapping("/{id}/comments")
    @Operation(summary = "Добавить комментарий")
    public Comment addComment(
            @PathVariable Integer id,
            @RequestBody CreateOrUpdateComment comment
    ) {
        return new Comment();
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
    public Comment updateComment(
            @PathVariable Integer adId,
            @PathVariable Integer commentId,
            @RequestBody CreateOrUpdateComment comment
    ) {
        return new Comment();
    }
}
