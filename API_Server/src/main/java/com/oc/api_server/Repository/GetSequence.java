package com.oc.api_server.Repository;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GetSequence {
    public long nextReview1_id();
}
