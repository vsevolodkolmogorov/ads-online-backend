package ru.skypro.homework.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.service.impl.ImageServiceImpl;

/**
 * Контроллер для работы с изображениями.
 * Этот контроллер предоставляет API для загрузки и получения изображений,
 * используемых в системе, таких как изображения объявлений или профилей пользователей.
 */
@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageServiceImpl imageServiceImpl;

    /**
     * Конструктор контроллера, принимающий сервис для работы с изображениями.
     *
     * @param imageServiceImpl Сервис для получения байтов изображений
     */
    public ImageController(ImageServiceImpl imageServiceImpl) {
        this.imageServiceImpl = imageServiceImpl;
    }

    /**
     * Возвращает изображение по его имени.
     *
     * @param filename Имя файла изображения
     * @return {@link ResponseEntity} с массивом байтов изображения и соответствующим заголовком Content-Type,
     * или статус 404, если изображение не найдено.
     */
    @GetMapping(value = "/{filename}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, "image/*"})
    public ResponseEntity<byte[]> getImage(@PathVariable String filename) {
        try {
            byte[] bytes = imageServiceImpl.getImageBytes(filename);
            return ResponseEntity.ok(bytes);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
