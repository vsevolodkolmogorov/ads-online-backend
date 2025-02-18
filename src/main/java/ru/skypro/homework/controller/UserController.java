package ru.skypro.homework.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
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
    public int setPassword(@RequestBody NewPasswordDTO password) {
        return 0;
    }

    @Operation(summary = "Получение информации об авторизованном пользователе")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "401")
    @GetMapping("/me")
    public UserDTO getAuthUser() {
        return new UserDTO();
    }

    @Operation(summary = "Обновление информации об авторизованном пользователе")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "401")
    @PatchMapping("/me")
    public UserDTO updateUser(@RequestBody UpdateUserDTO updateUser) {
        return new UserDTO();
    }

    @Operation(summary = "Обновление аватара авторизованного пользователя")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "401")
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UserDTO updateUserImage(@RequestParam("image") MultipartFile image) {
        return new UserDTO();
    }
}
