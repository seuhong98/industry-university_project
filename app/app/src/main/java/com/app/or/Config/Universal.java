package com.app.or.Config;

import com.app.or.Hide.Abbr;
import com.app.or.Hide.AbbrInterFace;
import com.app.or.Interface.SecurityInterface;
import com.app.or.Universal.HttpsHelper;
import com.app.or.Universal.ImageHelper;
import com.app.or.Universal.ReviewHelper;
import com.app.or.Universal.Security;

public class Universal {

    public static final AbbrInterFace abbr = new Abbr();
    public static final HttpsHelper httpsHelper = new HttpsHelper();
    public static final ImageHelper imageHelper = new ImageHelper();
    public static final ReviewHelper reviewHelper = new ReviewHelper();
    public static final SecurityInterface security = new Security();

}
