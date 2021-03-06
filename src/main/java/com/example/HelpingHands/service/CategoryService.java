package com.example.HelpingHands.service;


import com.example.HelpingHands.entity.CatEntity;
import com.example.HelpingHands.entity.CategoryEntity;
import com.example.HelpingHands.entity.DonateEntity;
import com.example.HelpingHands.entity.UserEntity;
import com.example.HelpingHands.repository.CategoryRepository;
import com.example.HelpingHands.repository.DonateRepository;
import com.example.HelpingHands.repository.UserRepository;
import com.example.HelpingHands.request.*;
import com.example.HelpingHands.response.PortalResponse;
import com.example.HelpingHands.utility.ImageResponse;
import com.example.HelpingHands.utility.VerifyToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.*;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    DonateRepository donateRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    VerifyToken verifyToken;

    @Autowired
    ImageResponse imageResponse;

    public PortalResponse categoryInfo(@RequestBody CategoryRequest req) {
        PortalResponse portalResponse=new PortalResponse();
            try {
                if (req.getCategoryName().equals("All")) {

                    List<CategoryEntity> categoryEntity = categoryRepository.findAll();
                    List<CatEntity> categoryEntities = new ArrayList<CatEntity>();
                    for(Long i=1L; i<=categoryEntity.size();++i){
                        Optional<CategoryEntity> categoryEntity1 = categoryRepository.findById(i);
                        if (categoryEntity != null) {
                            CatEntity catEntity = new CatEntity();
                            Long id = categoryEntity1.get().getCategoryID();
                            List<DonateEntity> donateEntity = donateRepository.getCategoryId(id);
//                            List<DonateEntity> donateEntityList = imageResponse.imageResponse(donateEntity);
                            catEntity.setCategoryID(i);
                            catEntity.setCategoryName(categoryEntity1.get().getCategoryName());
                            catEntity.setGetCategoryID(donateEntity);
                            categoryEntities.add(catEntity);
                        }
                    }
                    return portalResponse.commonSuccessResponse("Fetched all records", "", categoryEntities);
                }
                else {
                    Optional<CategoryEntity> categoryEntity = categoryRepository.findById(req.getCategoryID());
                    if (categoryEntity != null) {
                        Long id = categoryEntity.get().getCategoryID();
                        List<DonateEntity> donateEntity = donateRepository.getCategoryId(id);
                        List<DonateEntity> donateEntityList = imageResponse.imageResponse(donateEntity);

                        return portalResponse.customSuccessResponse("Fetched records","",donateEntityList);
                    }
                }
            }
            catch(Exception e){
                System.out.println(e.getMessage());
                e.printStackTrace();
                return  portalResponse.commonErrorResponse("No matched record found","","");
            }
        return null;
    }

    //========================================FETCH DATE VISE=================================================

    public PortalResponse fetchDateWise(DateRequest request){
        boolean flag = verifyToken.verifyToken(request.getUserID(), request.getToken());
        if(flag == true){
            LocalDate currentDate = LocalDate.now();
            LocalDate currentDateMinus1Week = currentDate.minusWeeks(1);
            LocalDate currentDateMinus1Month = currentDate.minusMonths(1);
            LocalDate currentDateMinus6Months = currentDate.minusMonths(6);

            Date today = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date week1 = Date.from(currentDateMinus1Week.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date month1 = Date.from(currentDateMinus1Month.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date month6 = Date.from(currentDateMinus6Months.atStartOfDay(ZoneId.systemDefault()).toInstant());

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String strToday = dateFormat.format(today);
            String strWeek1 = dateFormat.format(week1);
            String strMonth1 = dateFormat.format(month1);
            String strMonth6 = dateFormat.format(month6);

            List<DonateEntity> donateEntities = null;
            if(request.getFetchDate().equals("Today")){
                donateEntities = donateRepository.fetchDateWiseItems(strToday);
            }else if(request.getFetchDate().equals("OneWeek")){
                donateEntities = donateRepository.fetchDateWiseItems(strWeek1);
            }else if(request.getFetchDate().equals("OneMonth")){
                donateEntities = donateRepository.fetchDateWiseItems(strMonth1);
            }else if(request.getFetchDate().equals("SixMonths")){
                donateEntities = donateRepository.fetchDateWiseItems(strMonth6);
            }
            return PortalResponse.customSuccessResponse("Fetched data","",donateEntities);
        } else {
            return PortalResponse.commonErrorResponse("InvalidUser","","");
        }
    }


    }
