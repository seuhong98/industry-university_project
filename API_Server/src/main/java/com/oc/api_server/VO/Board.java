package com.oc.api_server.VO;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String title;
    String main;
    String photo;
    String address;
    byte water;
    byte sun;
    byte cold;
    byte clean;
    byte sound;
    byte owner;
    byte convenience;
    byte total;
}
