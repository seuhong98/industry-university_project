package com.app.or.Memory;

import com.app.or.Implementation.SimpleKey;

import java.security.interfaces.RSAPublicKey;
import java.util.Map;

public interface MemoryInterface {
    public String GetKakaoKey();
    public String URL();

    public String getFileDir();
    public void setFileDir(String filedir) ;

    public String getId();

    public void setId(String setId);

    public int getTextSizeDP();

    public void setTextSizeDP(int textSizeDP);

    public void setOrder(long start);

    public long getOrder();

    public RSAPublicKey getPublicKey(int i);

    public void setPublicKey(String[] publicKey);

    public int getPublicKeyVersion();

    public long getSessionTime();

    public Map<String,Integer> RegionToCode();
    public Map<Integer,String> CodeToRegion();
}