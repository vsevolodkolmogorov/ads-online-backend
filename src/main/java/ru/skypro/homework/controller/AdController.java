package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.service.AdService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
@Tag(name = "Объявления")
public class AdController {

    private final AdService adService;

    @GetMapping
    @Operation(summary = "Получить все объявления")
    public AdsDTO getAllAds() {
        return new AdsDTO();
    }

    @PostMapping(consumes = "multipart/form-data")
    @Operation(summary = "Добавить объявление")
    public ResponseEntity<AdDTO> addAd(
            @RequestPart("properties") CreateOrUpdateAdDTO properties,
            @RequestPart("image") MultipartFile image
    ) {
        AdDTO ad = new AdDTO();
        ad.setPk(1);
        return ResponseEntity.status(HttpStatus.CREATED).body(ad);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить объявление")
    public ResponseEntity<AdDTO> getAd(@PathVariable Integer id) {
        return ResponseEntity.ok(adService.getAdById(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить объявление")
    public ResponseEntity<Void> deleteAd(@PathVariable Integer id) {
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Обновить объявление")
    public AdDTO updateAd(
            @PathVariable Integer id,
            @RequestBody CreateOrUpdateAdDTO updateAd
    ) {
        return new AdDTO();
    }

    @GetMapping("/me")
    @Operation(summary = "Получить мои объявления")
    public AdsDTO getMyAds() {
        return new AdsDTO();
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
