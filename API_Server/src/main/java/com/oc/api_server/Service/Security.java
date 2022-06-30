package com.oc.api_server.Service;


import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Random;

public class Security {
    public String alg = "AES/CBC/PKCS5Padding";
    private final String key = "abcdefghabcdefghabcdefghabcdefgh"; // 32byte
    private String iv = "0123456789abcdef"; // 16byte


    private String  getIv(char first){
        Random random = new Random(first);
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<16;i++){
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    private String  getKey(char second){
        Random random = new Random(second);
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<16;i++){
            sb.append((char)(random.nextInt(25)+97));
        }
        return sb.toString();
    }



    // 암호화
    public String encryptAES256(String text) throws Exception {
        Cipher cipher = Cipher.getInstance(alg);
        SecretKeySpec keySpec = new SecretKeySpec(getKey(text.charAt(1)).getBytes(), "AES");
        IvParameterSpec ivParamSpec = new IvParameterSpec(getIv(text.charAt(0)).getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);

        byte[] encrypted = cipher.doFinal(text.getBytes("UTF-8"));
        return text.substring(0,2)+Base64.getEncoder().encodeToString(encrypted);
    }

    // 복호화
    public String decryptAES256(String cipherText) throws Exception {
        Cipher cipher = Cipher.getInstance(alg);
        SecretKeySpec keySpec = new SecretKeySpec(getKey(cipherText.charAt(1)).getBytes(), "AES");
        IvParameterSpec ivParamSpec = new IvParameterSpec(getIv(cipherText.charAt(0)).getBytes());
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);

        byte[] decodedBytes = Base64.getDecoder().decode(cipherText.substring(2));
        byte[] decrypted = cipher.doFinal(decodedBytes);
        return new String(decrypted, "UTF-8");
    }
}
