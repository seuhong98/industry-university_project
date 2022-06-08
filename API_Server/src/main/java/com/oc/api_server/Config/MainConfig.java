package com.oc.api_server.Config;

import com.oc.api_server.Repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class MainConfig {
    @Autowired
    DataSource dataSource;


}
