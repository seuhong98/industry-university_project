package com.oc.api_server.Service;


import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

public class Security {
    public String alg = "AES/CBC/PKCS5Padding";
    private final String key = "abcdefghabcdefghabcdefghabcdefgh"; // 32byte
    private String iv = "0123456789abcdef"; // 16byte


    public String  getCode(int number, int len){
        Random random = new Random(number);
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<len;i++){
            sb.append((char)(random.nextInt(25)+97));
        }
        return sb.toString();
    }

    private String makeSalt(int len){
        StringBuffer br = new StringBuffer();
        for(int i=0;i<len;i++){
            br.append((char)((int)(Math.random()*25)+97));
        }
        return br.toString();
    }

    private int makeRandomInt(){
        return (int)(Math.random()*900000+100000);
    }



    // 암호화
    public String TwoWayEncrypt(String text){
        try{
            int toKey = makeRandomInt();
            int toIv = makeRandomInt();
            Cipher cipher = Cipher.getInstance(alg);
            SecretKeySpec keySpec = new SecretKeySpec(getCode(toKey,32).getBytes(), "AES");
            IvParameterSpec ivParamSpec = new IvParameterSpec(getCode(toIv,16).getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);

            byte[] encrypted = cipher.doFinal(text.getBytes("UTF-8"));
            return toIv+""+toKey+Base64.getEncoder().encodeToString(encrypted);
        }catch (Exception e){
            return "FAIL";
        }
    }

    // 복호화
    public String TwoWayDecrypt(String cipherText){
        try {
            Cipher cipher = Cipher.getInstance(alg);
            SecretKeySpec keySpec = new SecretKeySpec(getCode(Integer.parseInt(cipherText.substring(6,12)),32).getBytes(), "AES");
            IvParameterSpec ivParamSpec = new IvParameterSpec(getCode(Integer.parseInt(cipherText.substring(0,6)),16).getBytes());
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);

            byte[] decodedBytes = Base64.getDecoder().decode(cipherText.substring(12));
            byte[] decrypted = cipher.doFinal(decodedBytes);
            return new String(decrypted, "UTF-8");
        }catch (Exception e){
            e.printStackTrace();
            return "FAIL";
        }
    }


    private String OneWayEncrypt(String txt, String salt){
        try {
            StringBuffer sbuf = new StringBuffer();
            MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
            mDigest.update((salt+txt).getBytes());
            byte[] msgStr = mDigest.digest() ;
            for(int j=0;j<5;j++){
                for(int i=0; i < msgStr.length; i++){
                    byte tmpStrByte = msgStr[i];
                    String tmpEncTxt = Integer.toString((tmpStrByte & 0xff) + 0x100, 16).substring(1);
                    sbuf.append(tmpEncTxt) ;
                }
                mDigest.update(sbuf.toString().getBytes());
                msgStr = mDigest.digest() ;
                if(!(j==4)){
                    sbuf.setLength(0);
                }
            }
            return salt+(sbuf.toString());
        }catch (Exception e){
            return "FAIL";
        }
    }


    public String makeOneWayEncrypt(String txt){
        String salt = makeSalt(5);
        return OneWayEncrypt(txt,salt);
    }

    public boolean match(String plain, String Want){
        if(Want.equals(OneWayEncrypt(plain,Want.substring(0,5)))){
            return true;
        }else {
            return false;
        }
    }

}
