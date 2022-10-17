package com.app.or.Config;

import com.app.or.FileSystem.FileSystem;
import com.app.or.Memory.Memory;
import com.app.or.Memory.MemoryInterface;
import com.app.or.Security.SecurityInterface;
import com.app.or.Network.Network;
import com.app.or.Universal.ImageHelper;
import com.app.or.Universal.ReviewHelper;
import com.app.or.Security.Security;

public class Universal {

    public static MemoryInterface memory;
    public static Network NETWORK;
    public static ImageHelper imageHelper;
    public static ReviewHelper reviewHelper;
    public static SecurityInterface security;
    public static FileSystem fileSystem;

    static public void UniversalInit() {
        security = new Security();
        security.MakePrivateKey();
        NETWORK = new Network();
        imageHelper = new ImageHelper();
        memory = new Memory();
        reviewHelper = new ReviewHelper();

        fileSystem = new FileSystem();
        if(fileSystem.isPublicKey()){
            memory.setPublicKey(fileSystem.GetPublicKey());
            if(memory.getPublicKeyVersion() != NETWORK.PublicKeyVersion()){
                NETWORK.SettingPublicKey();
                memory.setPublicKey(fileSystem.GetPublicKey());
            }
        }else{
            NETWORK.SettingPublicKey();
            memory.setPublicKey(fileSystem.GetPublicKey());
        }
    }
}
