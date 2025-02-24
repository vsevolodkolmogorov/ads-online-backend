package ru.skypro.homework.service.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.utility.SecurityUtil;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdServiceImpl implements AdService {

    private final AdMapper adMapper;
    private final AdRepository adRepository;
    private final UserServiceImpl userService;
    private final SecurityUtil securityUtil;

    public AdServiceImpl(AdMapper adMapper, AdRepository adRepository, UserServiceImpl userService, SecurityUtil securityUtil) {
        this.adMapper = adMapper;
        this.adRepository = adRepository;
        this.userService = userService;
        this.securityUtil = securityUtil;
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
    public AdDTO addAd(CreateOrUpdateAdDTO adDTO, MultipartFile image) {
        User author = userService.getCurrentUserEntity();

        Ad ad = new Ad()
                .setTitle(adDTO.getTitle())
                .setPrice(adDTO.getPrice())
                .setDescription(adDTO.getDescription())
                .setAuthor(author);
        adRepository.save(ad);
        return adMapper.adToAdDTO(ad);
    }

    @Override
    public void deleteAd(Integer id) {
        String username = securityUtil.getCurrentUsername();

        Ad ad = adRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ad not found"));
        if (!ad.getAuthor().getUsername().equals(username)) {
            throw new RuntimeException("You are not author of this ad");
        }
        adRepository.delete(ad);
    }

    @Override
    public AdsDTO getAllAds() {
        List<Ad> ads = adRepository.findAll();
        List<AdDTO> adDTOs = ads.stream()
                .map(adMapper::adToAdDTO)
                .collect(Collectors.toList());
        return new AdsDTO(adDTOs.size(), adDTOs);
    }

    @Override
    public AdDTO updateAd(Integer id, CreateOrUpdateAdDTO updateAd) {
        Ad ad = adRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ad not found"));
        ad.setTitle(updateAd.getTitle())
                .setPrice(updateAd.getPrice())
                .setDescription(updateAd.getDescription());
        adRepository.save(ad);
        return adMapper.adToAdDTO(ad);
    }

    @Override
    public AdsDTO getMyAds() {
        User user = userService.getCurrentUserEntity();
        List<Ad> ads = adRepository.findByAuthor(user);
        List<AdDTO> adDTOs = ads.stream()
                .map(adMapper::adToAdDTO)
                .collect(Collectors.toList());
        return new AdsDTO(adDTOs.size(), adDTOs);
    }

    /**
     * Обновление изображения товар.
     * TODO: Реализовать сохранение изображения в базу данных ( с использованием облачного хранилища ).
     */
    @Override
    public void updateAdImage(Integer id, MultipartFile image) {
        Ad ad = adRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ad not found"));
        ad.setImage("");
        adRepository.save(ad);
    }
}
