package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
@Tag(name = "Объявления")
public class AdController {

    @GetMapping
    @Operation(summary = "Получить все объявления")
    public Ads getAllAds() {
        return new Ads();
    }

    @PostMapping(consumes = "multipart/form-data")
    @Operation(summary = "Добавить объявление")
    public ResponseEntity<Ad> addAd(
            @RequestPart("properties") CreateOrUpdateAd properties,
            @RequestPart("image") MultipartFile image
    ) {
        Ad ad = new Ad();
        ad.setPk(1);
        return ResponseEntity.status(HttpStatus.CREATED).body(ad);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить объявление")
    public ExtendedAd getAd(@PathVariable Integer id) {
        return new ExtendedAd();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить объявление")
    public ResponseEntity<Void> deleteAd(@PathVariable Integer id) {
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Обновить объявление")
    public Ad updateAd(
            @PathVariable Integer id,
            @RequestBody CreateOrUpdateAd updateAd
    ) {
        return new Ad();
    }

    @GetMapping("/me")
    @Operation(summary = "Получить мои объявления")
    public Ads getMyAds() {
        return new Ads();
    }

    @PatchMapping(value = "/{id}/image", consumes = "multipart/form-data")
    @Operation(summary = "Обновить изображение")
    public ResponseEntity<Void> updateAdImage(
            @PathVariable Integer id,
            @RequestParam("image") MultipartFile image
    ) {
        return ResponseEntity.ok().build();
    }
}
