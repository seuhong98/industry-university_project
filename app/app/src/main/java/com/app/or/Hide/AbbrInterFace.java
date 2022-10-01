package com.app.or.Hide;

import java.security.Key;

public interface AbbrInterFace {
    public String GetKakaoKey();
    public String URL();

    public String getFiledir();


    public void setFiledir(String filedir) ;

    public String getId();

    public void setId(String setId);

    public int getTextSizeDP();

    public void setTextSizeDP(int textSizeDP);

    public Key getPublicKey();

    public void setPublicKey(Key setPublicKey);
}
