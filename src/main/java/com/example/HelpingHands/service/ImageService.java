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
            String staticPath = "src\\main\\resources\\static\\Photos";
            DonateEntity donateEntity1= new DonateEntity();
            donateRepository.save(donateEntity1);

            File file = new File(staticPath);
            String path = file.getAbsolutePath();

            String itemID = String.valueOf(donateRepository.maxItemID());
            String userID = String.valueOf(userId);

            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = dateFormat.format(date);

            makeDateDirectoryIfNotExist(path + "\\" + strDate);
            makeUserIDDirectoryIfNotExist(path + "\\" + strDate + "\\" + userID);
            makeItemIDDirectoryIfNotExist(path + "\\" + strDate + "\\" + userID + "\\" + itemID);

            String fullPath = path + "\\" + strDate + "\\" + userID + "\\" + itemID;
            String dynamicPath = url + "\\" + strDate + "\\" + userID + "\\" + itemID;

            SaveImage.saveImage(fileFullWidth,fullPath,"full-width.jpg");
            SaveImage.saveImage(fileThumbnail,fullPath,"thumbnail.jpg");
            SaveImage.saveImage(filePortrait,fullPath,"portrait.jpg");
            SaveImage.saveImage(fileSquare,fullPath,"square.jpg");
            SaveImage.saveImage(fileHero,fullPath,"hero.jpg");

            Optional<DonateEntity> donateEntity = saveDonateInfo.saveInfo(dynamicPath, category, item_name, item_desc, userId);

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