package com.oc.api_server.Repository;

import javax.persistence.EntityManager;

public class BoardRepository {

    private EntityManager em;
    public BoardRepository(EntityManager em){
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
