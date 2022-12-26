package com.tipeaky.peakystore.services;

import com.tipeaky.peakystore.model.entities.Gender;
import com.tipeaky.peakystore.model.enums.GenderEnum;
import com.tipeaky.peakystore.repositories.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenderService {

    @Autowired
    GenderRepository genderRepository;

    public void saveAll(List<GenderEnum> genderList) {
        for (GenderEnum gender1 : genderList) {
            Gender gender = new Gender();
            gender.setGenderEnum(gender1);
            genderRepository.save(gender);
        }
    }

}
