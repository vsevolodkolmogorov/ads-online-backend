package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.service.AdService;

@Service
public class AdServiceImpl implements AdService {

    private final AdMapper adMapper;

    public AdServiceImpl(AdMapper adMapper) {
        this.adMapper = adMapper;
    }

    public AdDTO getAdDTO(Ad ad) {
        return adMapper.adToAdDTO(ad);
    }

    public Ad createAdFromDTO(AdDTO adDTO) {
        return adMapper.adDTOToAd(adDTO);
    }
}
