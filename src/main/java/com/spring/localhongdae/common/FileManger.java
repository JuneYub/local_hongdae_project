package com.spring.localhongdae.common;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

@Component
public class FileManger {

    public String imageUpload(byte[] bytes, String originalFileName, String path) throws IOException {
        if(bytes == null) {
            return null;
        }

        if("".equals(originalFileName) || originalFileName == null) {
            return null;
        }

        String fileExt = originalFileName.substring(originalFileName.lastIndexOf("."));
        if(fileExt == null || "".equals(fileExt) || ".".equals(fileExt)) {
            return null;
        }

        String newFileName = String.format("%1$tY%1$tm%1$td%1$H%1tM%1$tS", Calendar.getInstance());
        newFileName += System.nanoTime();
        newFileName += fileExt;

        File dir = new File(path);

        if(!dir.exists()) {
            dir.mkdir();
        }

        String pathname = path + File.separator + newFileName;

        FileOutputStream fos = new FileOutputStream(pathname);

        fos.write(bytes);
        fos.close();

        return newFileName;
    };
}
