package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;

@Service
public class AdServiceImpl implements AdService {

    private final AdMapper adMapper;
    private final AdRepository adRepository;
    private final UserRepository userRepository;

    public AdServiceImpl(AdMapper adMapper, AdRepository adRepository, UserRepository userRepository) {
        this.adMapper = adMapper;
        this.adRepository = adRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AdDTO getAdDTO(Ad ad) {
        return adMapper.adToAdDTO(ad);
    }

    @Override
    public Ad createAdFromDTO(AdDTO adDTO) {
        return adMapper.adDTOToAd(adDTO);
    }

    @Override
    public AdDTO getAdById(Integer id) {
        Ad ad = adRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ad not found"));
        return adMapper.adToAdDTO(ad);
    }

    @Override
    public AdDTO addAd(CreateOrUpdateAdDTO adDTO, String username) {
        User author = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Ad ad = new Ad()
                .setTitle(adDTO.getTitle())
                .setPrice(adDTO.getPrice())
                .setAuthor(author);

        adRepository.save(ad);
        return adMapper.adToAdDTO(ad);
    }

    @Override
    public void deleteAd(Integer id, String username) {
        Ad ad = adRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ad not found"));

        if (!ad.getAuthor().getEmail().equals(username)) {
            throw new RuntimeException("You are not author of this ad");
        }

        adRepository.delete(ad);
    }
}
