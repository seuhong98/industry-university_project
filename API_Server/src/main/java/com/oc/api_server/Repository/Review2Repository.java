package com.oc.api_server.Repository;

import javax.persistence.EntityManager;

public class Review2Repository {

    private EntityManager em;
    public Review2Repository(EntityManager em){
        this.em = em;
    }


    public boolean CreateBoard() {
        return false;
    }

    public boolean RemoveBoard() {
        return false;
    }

    public boolean UpdateBoard() {
        return false;
    }

    public boolean DeleteBoard() {
        return false;
    }
}
