package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.model.Ad;

@Mapper(componentModel = "spring")
public interface AdMapper {

    @Mapping(source = "author.id", target = "author")
    AdDTO adToAdDTO(Ad ad);

    @Mapping(source = "author", target = "author.id")
    Ad adDTOToAd(AdDTO adDTO);
}
