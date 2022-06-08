package com.oc.api_server.Repository;

import javax.sql.DataSource;

public interface UserRepository {
    public boolean CreateUser();
    public boolean RemoveUser();
    public boolean UpdateUser();
    public boolean DeleteUser();

}
