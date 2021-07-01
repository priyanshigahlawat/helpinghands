package com.example.HelpingHands.utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

@Service
public class SaveImage {

    public static String saveImage(ArrayList<MultipartFile> fileFullWidth,String strDate, String userID, String itemID, String url,String name) throws IOException {
        String dynamicPath1 = "" + strDate + "\\" + userID +  "\\" + itemID + "\\" + name;
        File ff1 = new File(String.valueOf(url + "\\" + strDate + "\\" + userID +  "\\" + itemID),name);
        FileOutputStream fos1 = new FileOutputStream(ff1);
        fos1.write(fileFullWidth.get(0).getBytes());
        fos1.close();
        return dynamicPath1;
    }
}
