package com.app.or.Config;

import com.app.or.FileSystem.FileSystem;
import com.app.or.Memory.Memory;
import com.app.or.Memory.MemoryInterface;
import com.app.or.Security.SecurityInterface;
import com.app.or.Network.Network;
import com.app.or.Universal.DataMapper;
import com.app.or.Universal.ImageHelper;
import com.app.or.Security.Security;

import java.util.Arrays;

public class Universal {

    public static MemoryInterface memory = new Memory();;
    public static Network NETWORK = new Network();;
    public static ImageHelper imageHelper = new ImageHelper();;
    public static SecurityInterface security = new Security();
    public static FileSystem fileSystem = new FileSystem();;
    public static DataMapper dataMapper = new DataMapper();;

    static public void UniversalInit() {
        security.init();

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
