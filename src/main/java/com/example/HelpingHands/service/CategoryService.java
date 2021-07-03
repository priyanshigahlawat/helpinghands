package com.example.HelpingHands.service;


import com.example.HelpingHands.entity.CategoryEntity;
import com.example.HelpingHands.entity.DonateEntity;
import com.example.HelpingHands.entity.UserEntity;
import com.example.HelpingHands.repository.CategoryRepository;
import com.example.HelpingHands.repository.DonateRepository;
import com.example.HelpingHands.repository.UserRepository;
import com.example.HelpingHands.request.CategoryRequest;
import com.example.HelpingHands.request.LoginRequest;
import com.example.HelpingHands.response.PortalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    DonateRepository donateRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public PortalResponse categoryInfo(@RequestBody CategoryRequest req) {
        PortalResponse portalResponse=new PortalResponse();
        try {
            if (req.getCategoryName().equals("All")) {
                List<DonateEntity> donateEntity = donateRepository.findAll();
                return portalResponse.commonSuccessResponse("Fetched all records", "", donateEntity);
            } else {
                Optional<CategoryEntity> categoryEntity = categoryRepository.findById(req.getCategoryID());
                if (categoryEntity != null) {
                    Long id = categoryEntity.get().getCategoryID();
                    List<DonateEntity> donateEntityList = donateRepository.getCategoryId(id);

                    return portalResponse.customSuccessResponse("fetched record","",donateEntityList);

//                    ListIterator<DonateEntity> listIterator = donateEntityList.listIterator();
//
//                    while (listIterator.hasNext()) {
//                        DonateEntity d1 = listIterator.next();
//                        if (d1.getAprrovedStatus() == 1 && d1.getExpireStatus() == 1) {
//                            return portalResponse.commonSuccessResponse("fetched", "", d1);
//                        }
//                    }
                }
            }
        }
        catch(Exception e){
            return  portalResponse.commonErrorResponse("No matched record found","","");
        }
        return null;
    }
}
