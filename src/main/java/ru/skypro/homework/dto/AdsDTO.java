package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;

@Data
public class AdsDTO {

    private int count;
    private AdDTO ad;

    private List<AdDTO> results;
    public AdsDTO(int count, List<AdDTO> results) {
        this.count = count;
        this.results = results;
    }
}
