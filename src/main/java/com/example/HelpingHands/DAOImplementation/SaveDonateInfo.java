package com.example.HelpingHands.DAOImplementation;

import com.example.HelpingHands.entity.DonateEntity;
import com.example.HelpingHands.repository.DonateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class SaveDonateInfo {

    @Autowired
    DonateRepository donateRepository;

    public Optional<DonateEntity> saveInfo(String dynamicPath1,
                                           String dynamicPath2,
                                           String dynamicPath3,
                                           String dynamicPath4,
                                           String dynamicPath5,
                                           Long category,
                                           String item_name,
                                           String item_desc,
                                           Long userID){
        Optional<DonateEntity> donateEntity = donateRepository.findById(donateRepository.max());
        donateEntity.get().setFull_width(dynamicPath1);
        donateEntity.get().setThumbnail(dynamicPath2);
        donateEntity.get().setPortrait(dynamicPath3);
        donateEntity.get().setSquare(dynamicPath4);
        donateEntity.get().setHero(dynamicPath5);
        donateEntity.get().setCategoryID(category);
        donateEntity.get().setItemDesc(item_desc);
        donateEntity.get().setItemName(item_name);
        donateEntity.get().setUserID(userID);
        donateEntity.get().setAprrovedStatus(0L);
        donateEntity.get().setExpireStatus(0L);
        donateEntity.get().setDate(new Date(System.currentTimeMillis()));
        donateRepository.save(donateEntity.get());

        return donateEntity;
    }
}
