package com.example.HelpingHands.service;

import com.example.HelpingHands.entity.DonateEntity;
import com.example.HelpingHands.repository.DonateRepository;
import com.example.HelpingHands.response.PortalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.sampled.Port;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class ImageService {

    @Value("${imageUrl}")
    private String url;

    @Autowired
    DonateRepository donateRepository;

    public PortalResponse imageUpload(@RequestParam String userID,
                                      @RequestParam ArrayList<MultipartFile> fileFullWidth,
                                      @RequestParam ArrayList<MultipartFile> fileThumbnail,
                                      @RequestParam ArrayList<MultipartFile> filePortrait,
                                      @RequestParam ArrayList<MultipartFile> fileSquare,
                                      @RequestParam ArrayList<MultipartFile> fileHero) throws IOException {

        DonateEntity donateEntity = new DonateEntity();
        PortalResponse portalResponse = new PortalResponse();

        Date date = new Date(System.currentTimeMillis());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String strDate = dateFormat.format(date);

        makeDateDirectoryIfNotExist(url + "\\" + strDate);
        makeUserIDDirectoryIfNotExist(url + "\\" + strDate + "\\" + userID);

        //============================FULL WIDTH==================================
        String dynamicPath1 = "" + strDate + "\\" + userID + "\\full-width.jpg";
        File ff1 = new File(String.valueOf(url + "\\" + strDate + "\\" + userID),"full-width.jpg");
        FileOutputStream fos1 = new FileOutputStream(ff1);
        fos1.write(fileFullWidth.get(0).getBytes());
        fos1.close();

        //============================THUMBNAIL===================================
        String dynamicPath2 = "" + strDate + "\\" + userID + "\\thumbnail.jpg";
        File ff2 = new File(String.valueOf(url + "\\" + strDate + "\\" + userID),"thumbnail.jpg");
        FileOutputStream fos2 = new FileOutputStream(ff2);
        fos2.write(fileThumbnail.get(0).getBytes());
        fos2.close();

        //============================PORTRAIT=====================================
        String dynamicPath3 = "" + strDate + "\\" + userID + "\\portrait.jpg";
        File ff3 = new File(String.valueOf(url + "\\" + strDate + "\\" + userID),"portrait.jpg");
        FileOutputStream fos3 = new FileOutputStream(ff3);
        fos3.write(filePortrait.get(0).getBytes());
        fos3.close();

        //============================SQUARE=========================================
        String dynamicPath4 = "" + strDate + "\\" + userID + "\\square.jpg";
        File ff4 = new File(String.valueOf(url + "\\" + strDate + "\\" + userID),"square.jpg");
        FileOutputStream fos4 = new FileOutputStream(ff4);
        fos4.write(fileSquare.get(0).getBytes());
        fos4.close();

        //============================HERO===========================================
        String dynamicPath5 = "" + strDate + "\\" + userID + "\\hero.jpg";
        File ff5 = new File(String.valueOf(url + "\\" + strDate + "\\" + userID),"hero.jpg");
        FileOutputStream fos5 = new FileOutputStream(ff5);
        fos5.write(fileHero.get(0).getBytes());
        fos5.close();

        donateEntity.setFull_width(dynamicPath1);
        donateEntity.setThumbnail(dynamicPath2);
        donateEntity.setPortrait(dynamicPath3);
        donateEntity.setSquare(dynamicPath4);
        donateEntity.setHero(dynamicPath5);
        donateEntity.setDate(new Date(System.currentTimeMillis()));
        donateRepository.save(donateEntity);

        portalResponse.setStatusCode("200");
        portalResponse.setMessage("Image Uploaded");

        return portalResponse;
    }
    
    public void makeDateDirectoryIfNotExist(String url){
        File directory = new File(url);
        if(!directory.exists()){
            directory.mkdir();
        }
    }

    public void makeUserIDDirectoryIfNotExist(String url){
        File directory = new File(url);
        if(!directory.exists()){
            directory.mkdir();
        }
    }

}