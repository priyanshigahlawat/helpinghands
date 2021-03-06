package com.example.HelpingHands.controller;

import com.example.HelpingHands.request.*;
import com.example.HelpingHands.response.PortalResponse;
import com.example.HelpingHands.service.AdminStatsService;
import com.example.HelpingHands.service.CategoryService;
import com.example.HelpingHands.service.ImageService;
import com.example.HelpingHands.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "*")
public class DonorController {

    @Autowired
    ImageService imageService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    UpdateService updateService;



    @PostMapping("/uploadImage")
    public PortalResponse imageUpload(@RequestParam String token,
                                      @RequestParam Long userID,
                                      @RequestParam Long category,
                                      @RequestParam String item_desc,
                                      @RequestParam String item_name,
                                      @RequestParam ArrayList<MultipartFile> fileFullWidth,
                                      @RequestParam ArrayList<MultipartFile> fileThumbnail,
                                      @RequestParam ArrayList<MultipartFile> filePortrait,
                                      @RequestParam ArrayList<MultipartFile> fileSquare,
                                      @RequestParam ArrayList<MultipartFile> fileHero)throws IOException {
        return imageService.imageUpload(token, userID, category,item_desc, item_name, fileFullWidth, fileThumbnail, filePortrait, fileSquare, fileHero);
    }

    @PostMapping("/category")
    public PortalResponse category(@RequestBody CategoryRequest req){
        return categoryService.categoryInfo(req);
    }

    @PostMapping("/fetchDateWise")
    public PortalResponse fetchDateWise(@RequestBody DateRequest request){
        return categoryService.fetchDateWise(request);
    }

    @PostMapping("/updateInformation")
    public PortalResponse updateInformation(@RequestBody UpdateRequest req){
        return updateService.updateInformation(req);
    }

    @PostMapping("/updatePassword")
    public PortalResponse updatePassword(@RequestBody PasswordRequest req){
        return updateService.updatePassword(req);
    }


}