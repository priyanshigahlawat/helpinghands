package com.example.HelpingHands.DAOImplementation;

import com.example.HelpingHands.entity.CategoryEntity;
import com.example.HelpingHands.entity.DonateEntity;
import com.example.HelpingHands.repository.CategoryRepository;
import com.example.HelpingHands.repository.DonateRepository;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class SaveDonateInfo {

    @Autowired
    DonateRepository donateRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public Optional<DonateEntity> saveInfo(String dynamicPath,
                                           Long category,
                                           String item_name,
                                           String item_desc,
                                           Long userID){
        Optional<DonateEntity> donateEntity = donateRepository.findById(donateRepository.maxItemID());
        System.out.println("max" + donateRepository.maxItemID());
        Optional<CategoryEntity> categoryEntity = categoryRepository.findById(category);
        List<DonateEntity> donateEntityList = categoryEntity.get().getGetCategoryID();

        System.out.println("donateEntity:  " + donateEntity);

        donateEntity.get().setFull_width(dynamicPath+"\\full-width.jpg");
        donateEntity.get().setThumbnail(dynamicPath+"\\thumbnail.jpg");
        donateEntity.get().setPortrait(dynamicPath+"\\portrait.jpg");
        donateEntity.get().setSquare(dynamicPath+"\\square.jpg");
        donateEntity.get().setHero(dynamicPath+"\\hero.jpg");
        donateEntity.get().setItemDesc(item_desc);
        donateEntity.get().setItemName(item_name);
        donateEntity.get().setUserID(userID);
        donateEntity.get().setAprrovedStatus(0L);
        donateEntity.get().setExpireStatus(0L);
        donateEntity.get().setDate(new Date());
        donateRepository.save(donateEntity.get());

        donateEntityList.add(donateEntity.get());
        categoryEntity.get().setGetCategoryID(donateEntityList);
        categoryRepository.save(categoryEntity.get());

        return donateEntity;
    }
}
