package com.app.or.Universal;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ImageHelper {
    public static String ImageToString(Bitmap image){
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG,100,baos);
            byte[] array = baos.toByteArray();
            StringBuffer sb = new StringBuffer();
            sb.append(array.length+" ");
            for(byte t : array){
                sb.append(t+" ");
            }
            sb.setLength(sb.length()-1);
            return sb.toString();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap StringToImage(String input){
        StringTokenizer str = new StringTokenizer(input);
        int len = Integer.parseInt(str.nextToken());
        byte[] makeBitmap = new byte[len];
        for(int i=0;i<len;i++){
            makeBitmap[i] = Byte.parseByte(str.nextToken());
        }
        return BitmapFactory.decodeByteArray(makeBitmap,0, makeBitmap.length);
    }
}
