package com.example.HelpingHands.utility;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

@Service
public class SaveImage {

    public static int saveImage(ArrayList<MultipartFile> fileFullWidth, String fullPath, String name) throws IOException {
        File ff1 = new File(fullPath,name);
        FileOutputStream fos1 = new FileOutputStream(ff1);
        fos1.write(fileFullWidth.get(0).getBytes());
        fos1.flush();
        fos1.close();
        return 0;
    }
}
