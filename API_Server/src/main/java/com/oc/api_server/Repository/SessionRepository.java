package com.oc.api_server.Repository;

import com.oc.api_server.VO.publicKey;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface SessionRepository {
    public List<publicKey> getPublicKey();
    public publicKey getCurrent();
}
