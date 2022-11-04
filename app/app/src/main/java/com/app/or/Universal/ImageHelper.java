package com.app.or.Universal;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ImageHelper {
    public String ImageToString(Bitmap image){
        try {

            long size_t =image.getByteCount();
            if(size_t>=1500000){
                long t = size_t/1500000;
                image = Bitmap.createScaledBitmap(image,(int)(image.getWidth()/t),(int)(image.getHeight()/t),true);
            }
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

    public Bitmap StringToImage(String input){
        StringTokenizer str = new StringTokenizer(input);
        int len = Integer.parseInt(str.nextToken());
        byte[] makeBitmap = new byte[len];
        for(int i=0;i<len;i++){
            makeBitmap[i] = Byte.parseByte(str.nextToken());
        }
        return BitmapFactory.decodeByteArray(makeBitmap,0, makeBitmap.length);
    }
}
