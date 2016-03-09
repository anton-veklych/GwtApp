package com.gwtApp.server.DAO;


import com.gwtApp.Entity.User;

import java.util.List;

public interface UserDAO {
    public void initial();
    public List<User> getUsersList(User user);
}
