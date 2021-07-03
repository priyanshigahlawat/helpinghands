package com.example.HelpingHands.utility;

import com.example.HelpingHands.entity.DonateEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ImageResponse {

    @Value("${imageUrl}")
    private String url;

    public List<DonateEntity> imageResponse(List<DonateEntity> donateEntityList){

        ListIterator<DonateEntity> listIterator =  donateEntityList.listIterator();
        while (listIterator.hasNext()){
            DonateEntity d1 = listIterator.next();
            String fullWidth = url + "\\" + d1.getFull_width();
            String thumbnail = url + "\\" + d1.getThumbnail();
            String portrait = url + "\\" + d1.getPortrait();
            String square = url + "\\" + d1.getSquare();
            String hero = url + "\\" + d1.getHero();
            System.out.println(fullWidth);
            System.out.println(thumbnail);
            d1.setFull_width(fullWidth);
            d1.setThumbnail(thumbnail);
            d1.setPortrait(portrait);
            d1.setSquare(square);
            d1.setHero(hero);
        }

        return donateEntityList;
    }
}
