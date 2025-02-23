package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.model.Ad;

public interface AdService {
    AdDTO getAdDTO(Ad ad);
    Ad createAdFromDTO(AdDTO adDTO);

    AdDTO getAdById(Integer id);

    AdDTO addAd(CreateOrUpdateAdDTO adDTO, MultipartFile image, String username);

    void deleteAd(Integer id, String username);

    AdsDTO getAllAds();

    AdDTO updateAd(Integer id, CreateOrUpdateAdDTO updateAd);

    AdsDTO getMyAds();

    void updateAdImage(Integer id, MultipartFile image);
}
