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


    public void setOrder(long start);

    public long getOrder();

    public RSAPublicKey getPublicKey(int i);

    public void setPublicKey(String[] publicKey);

    public int getPublicKeyVersion();

    public long getSessionTime();

    public Integer RegionToCode(String input);
    public String CodeToRegion(int input);

    public Integer getUniCode(String input);

    public int getX();
    public void setX(int x);
    public int getY();
    public void setY(int y);

    public int getTextSize();
    public void setTextSize(int textSize);
    public int getTextBox();
    public void setTextBox(int textBox);


    public String getUniName();

    public void setUniName(String uniName);

    public double getUni_y();
    public double getUni_x();

    public int UniAddressSize();
}
