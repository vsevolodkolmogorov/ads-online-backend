package ru.skypro.homework.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/user")
public class UserController {

    @Operation(summary = "Обновление пароля")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "401")
    @ApiResponse(responseCode = "403")
    @PatchMapping("/password")
    @PostMapping("/set_password")
    public int setPassword(@RequestBody NewPassword password) {
        return 0;
    }

    @Operation(summary = "Получение информации об авторизованном пользователе")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "401")
    @GetMapping("/me")
    public User getAuthUser() {
        return new User();
    }

    @Operation(summary = "Обновление информации об авторизованном пользователе")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "401")
    @PatchMapping("/me")
    public User updateUser(@RequestBody UpdateUser updateUser) {
        return new User();
    }

    @Operation(summary = "Обновление аватара авторизованного пользователя")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "401")
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public User updateUserImage(@RequestParam("image") MultipartFile image) {
        return new User();
    }
}
