package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.exception.UnauthorizedAdDeletionException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.mapper.ExtendedAdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация интерфейса {@link AdService}, предоставляющая операции, связанные с товарами.
 */
@Slf4j
@Service
public class AdServiceImpl implements AdService {

    private final AdMapper adMapper;
    private final AdRepository adRepository;
    private final UserServiceImpl userService;
    private final ExtendedAdMapper extendedAdMapper;
    private final ImageServiceImpl imageServiceImpl;
    private final UserRepository userRepository;

    public AdServiceImpl(AdMapper adMapper, AdRepository adRepository, UserServiceImpl userService, ExtendedAdMapper extendedAdMapper, ImageServiceImpl imageServiceImpl, UserRepository userRepository) {
        this.adMapper = adMapper;
        this.adRepository = adRepository;
        this.userService = userService;
        this.extendedAdMapper = extendedAdMapper;
        this.imageServiceImpl = imageServiceImpl;
        this.userRepository = userRepository;
    }

    /**
     * Преобразует объект {@link Ad} в {@link AdDTO}.
     *
     * @param ad объект объявления
     * @return DTO объявления
     */
    @Override
    public AdDTO getAdDTO(Ad ad) {
        return adMapper.adToAdDTO(ad);
    }

    /**
     * Создает объект {@link Ad} из {@link AdDTO}.
     *
     * @param adDTO DTO объявления
     * @return созданный объект объявления
     */
    @Override
    public Ad createAdFromDTO(AdDTO adDTO) {
        return adMapper.adDTOToAd(adDTO);
    }

    /**
     * Получает DTO объявления по его идентификатору.
     *
     * @param id идентификатор объявления
     * @return DTO объявления
     */
    @Override
    public ExtendedAdDTO getAdById(Integer id) {
        Ad ad = adRepository.findById(id).orElseThrow(() -> new RuntimeException("Ad not found"));
        ExtendedAdDTO extendedAdDTO = extendedAdMapper.adToExtendedAdDTO(ad);

        extendedAdDTO.setEmail(ad.getAuthor().getUsername());
        extendedAdDTO.setAuthorFirstName(ad.getAuthor().getFirstName());
        extendedAdDTO.setAuthorLastName(ad.getAuthor().getLastName());

        return extendedAdDTO;
    }

    /**
     * Добавляет новое объявление с изображением.
     *
     * @param adDTO данные объявления
     * @param image изображение объявления
     * @return DTO созданного объявления
     */
    @Override
    public AdDTO addAd(CreateOrUpdateAdDTO adDTO, MultipartFile image) {
        try {
            User author = userService.getCurrentUserEntity();
            String imageFileName = imageServiceImpl.saveImage(image);

            Ad ad = new Ad().setTitle(adDTO.getTitle()).setPrice(adDTO.getPrice()).setDescription(adDTO.getDescription()).setAuthor(author).setImage(imageFileName);

            ad = adRepository.save(ad);

            return adMapper.adToAdDTO(ad);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save ad image", e);
        }
    }

    /**
     * Удаляет объявление по его идентификатору.
     *
     * @param id идентификатор объявления
     */
    @Override
    public void deleteAd(Integer id) {
        User currentUser = userService.getCurrentUserEntity();
        Ad ad = adRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Ad not found with id: {}", id);
                    return new AdNotFoundException("Ad not found");
                });

        if (currentUser.getRole() != RoleDTO.ADMIN && ad.getAuthor().getId() != currentUser.getId()) {
            log.error("User {} is not authorized to delete ad {}", currentUser.getUsername(), id);
            throw new UnauthorizedAdDeletionException("You are not author of this ad");
        }

        adRepository.delete(ad);
        log.info("Ad {} deleted by user {}", id, currentUser.getUsername());
    }


    /**
     * Получает все объявления.
     *
     * @return DTO всех объявлений
     */
    @Override
    public AdsDTO getAllAds() {
        List<Ad> ads = adRepository.findAll();
        List<AdDTO> adDTOs = ads.stream().map(adMapper::adToAdDTO).collect(Collectors.toList());
        return new AdsDTO(adDTOs.size(), adDTOs);
    }

    /**
     * Обновляет объявление по его идентификатору.
     *
     * @param id       идентификатор объявления
     * @param updateAd данные для обновления
     * @return обновленное объявление
     */
    @Override
    public AdDTO updateAd(Integer id, CreateOrUpdateAdDTO updateAd) {
        User currentUser = userService.getCurrentUserEntity();
        Ad ad = adRepository.findById(id).orElseThrow(() -> new RuntimeException("Ad not found"));
        if (currentUser.getRole() != RoleDTO.ADMIN && ad.getAuthor().getId() != currentUser.getId()) {
            throw new RuntimeException("You are not author of this ad");
        }
        ad.setTitle(updateAd.getTitle()).setPrice(updateAd.getPrice()).setDescription(updateAd.getDescription());
        adRepository.save(ad);
        return adMapper.adToAdDTO(ad);
    }

    /**
     * Получает объявления текущего пользователя.
     *
     * @return DTO объявлений пользователя
     */
    @Override
    public AdsDTO getMyAds() {
        User user = userService.getCurrentUserEntity();
        List<Ad> ads = adRepository.findByAuthor(user);
        List<AdDTO> adDTOs = ads.stream().map(adMapper::adToAdDTO).collect(Collectors.toList());
        return new AdsDTO(adDTOs.size(), adDTOs);
    }

    /**
     * Проверяет, является ли пользователь автором объявления.
     *
     * @param adId     идентификатор объявления
     * @param userId имя пользователя
     * @return true, если пользователь - автор объявления
     */
    @Override
    public boolean isAdAuthor(Integer adId, Integer userId) {
        Ad ad = adRepository.findById(adId).orElseThrow(() -> new RuntimeException("Ad not found"));
        return ad.getAuthor().getId() == userId;
    }

    /**
     * Обновляет изображение объявления.
     *
     * @param id    идентификатор объявления
     * @param image новое изображение
     */
    @Override
    public void updateAdImage(Integer id, MultipartFile image) {
        try {
            Ad ad = adRepository.findById(id).orElseThrow(() -> new RuntimeException("Ad not found"));
            String fileName = imageServiceImpl.saveImage(image);
            ad.setImage(fileName);
            adRepository.save(ad);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save image", e);
        }
    }
}
