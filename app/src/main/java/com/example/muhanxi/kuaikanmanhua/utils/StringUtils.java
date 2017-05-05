package com.example.muhanxi.kuaikanmanhua.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by muhanxi on 17/4/24.
 */

public class StringUtils {



    public static  String inputStreamToString(InputStream inputStream) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        byte [] buffer = new byte[1024];

        int length = 0 ;

        try {
            while ((length = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer,0,length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return  byteArrayOutputStream.toString() ;
        }

    }
}
