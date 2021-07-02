package com.example.HelpingHands.service;

import com.example.HelpingHands.DAOImplementation.SaveDonateInfo;
import com.example.HelpingHands.entity.CategoryEntity;
import com.example.HelpingHands.entity.DonateEntity;
import com.example.HelpingHands.entity.OtpEntity;
import com.example.HelpingHands.repository.CategoryRepository;
import com.example.HelpingHands.repository.DonateRepository;
import com.example.HelpingHands.request.TokenRequest;
import com.example.HelpingHands.response.PortalResponse;
import com.example.HelpingHands.utility.SaveImage;
import com.example.HelpingHands.utility.VerifyToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
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
import java.util.Optional;

@Service
public class ImageService {

    @Value("${imageUrl}")
    private String url;

    @Autowired
    DonateRepository donateRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    VerifyToken verifyToken;

    @Autowired
    SaveDonateInfo saveDonateInfo;

    public PortalResponse imageUpload(@RequestParam String token,
                                      @RequestParam Long userId,
                                      @RequestParam Long category,
                                      @RequestParam String item_desc,
                                      @RequestParam String item_name,
                                      @RequestParam ArrayList<MultipartFile> fileFullWidth,
                                      @RequestParam ArrayList<MultipartFile> fileThumbnail,
                                      @RequestParam ArrayList<MultipartFile> filePortrait,
                                      @RequestParam ArrayList<MultipartFile> fileSquare,
                                      @RequestParam ArrayList<MultipartFile> fileHero) throws IOException {

        boolean flag = verifyToken.verifyToken(userId,token);
        PortalResponse portalResponse = new PortalResponse();
        if(flag == true){
            Optional<CategoryEntity> categoryEntity = categoryRepository.findById(category);

            DonateEntity donateEntity1 = new DonateEntity();
            donateRepository.save(donateEntity1);

            String itemID = String.valueOf(donateRepository.max());
            String userID = String.valueOf(userId);

            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = dateFormat.format(date);
            System.out.println(strDate);

            makeDateDirectoryIfNotExist(url + "\\" + strDate);
            makeUserIDDirectoryIfNotExist(url + "\\" + strDate + "\\" + userID);
            makeItemIDDirectoryIfNotExist(url + "\\" + strDate + "\\" + userID + "\\" + itemID);

            String dynamicPath1 = SaveImage.saveImage(fileFullWidth,strDate,userID,itemID,url,"full-width.jpg");
            String dynamicPath2 = SaveImage.saveImage(fileThumbnail,strDate,userID,itemID,url,"thumbnail.jpg");
            String dynamicPath3 = SaveImage.saveImage(filePortrait,strDate,userID,itemID,url,"portrait.jpg");
            String dynamicPath4 = SaveImage.saveImage(fileSquare,strDate,userID,itemID,url,"square.jpg");
            String dynamicPath5 = SaveImage.saveImage(fileHero,strDate,userID,itemID,url,"hero.jpg");

            Optional<DonateEntity> donateEntity = saveDonateInfo.saveInfo(dynamicPath1,dynamicPath2,dynamicPath3,dynamicPath4,
                    dynamicPath5, category, item_name, item_desc, userId);

            return portalResponse.commonSuccessResponse("Image Uploaded","",donateEntity);
        } else {
            return portalResponse.commonErrorResponse("invalid user", "", "");
        }

    }

    public void makeDateDirectoryIfNotExist(String url){
        File directory = new File(url);
        if(!directory.exists()){
            directory.mkdir();
        }
    }

    public void makeItemIDDirectoryIfNotExist(String url){
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