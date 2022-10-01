package com.app.or.DTO;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

public class Address implements Parcelable {
    String address1;
    String address2;
    Double x;
    Double y;




    public Address(Parcel parcel){
        address1 = parcel.readString();
        address2 = parcel.readString();
        x = parcel.readDouble();
        y = parcel.readDouble();
    }

    public Address(String a){
        String[] list = a.split(",");
        int len = 0;
        boolean doing[] = new boolean[4];
        for(String t : list){

            if(len ==4){
                break;
            }

            if((!doing[0]) && t.contains("region_3depth_h_name")){
               address1 = t.substring(24,t.length()-1);
               doing[0] = true;
               len++;
               continue;
            }
            if((!doing[1]) && t.contains("region_3depth_name")){
                address2 = t.substring(22,t.length()-1);
                doing[1] = true;
                len++;
                continue;
            }
            if((!doing[2]) && t.contains("\"x\":\"")){
                x=Double.parseDouble(t.substring(5,t.length()-1));
                doing[2] = true;
                len++;
                continue;
            }
            if((!doing[3]) && t.contains("\"y\":\"")){
                y=Double.parseDouble(t.substring(5,t.length()-2));
                doing[3] = true;
                len++;
                continue;
            }
        }
    }

    public static final Creator<Address> CREATOR = new Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean isNull(){
        boolean isn = false;
        isn = address1==null ? true: false;
        isn = address2==null ? true: false;
        isn = x==null ? true: false;
        isn = y==null ? true: false;
        return isn;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(address1);
        parcel.writeString(address2);
        parcel.writeDouble(x);
        parcel.writeDouble(y);
    }
}
