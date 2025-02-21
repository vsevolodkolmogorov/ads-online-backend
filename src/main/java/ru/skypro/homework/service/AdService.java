package ru.skypro.homework.service;

import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.model.Ad;

public interface AdService {
    AdDTO getAdDTO(Ad ad);
    Ad createAdFromDTO(AdDTO adDTO);

    AdDTO getAdById(Integer id);

    AdDTO addAd(CreateOrUpdateAdDTO adDTO, String username);

    void deleteAd(Integer id, String username);
}
