package com.oc.api_server.Service;

import com.oc.api_server.Repository.Review1Repository;
import com.oc.api_server.Repository.Review2Repository;

import java.util.List;

public class Review2Service {
    private final Review2Repository br;

    public Review2Service(Review2Repository br) {
        this.br = br;
    }

}
