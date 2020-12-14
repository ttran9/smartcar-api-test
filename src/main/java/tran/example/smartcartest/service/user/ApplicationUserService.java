package tran.example.smartcartest.service.user;

import tran.example.smartcartest.model.domain.ApplicationUser;

import java.util.List;

public interface ApplicationUserService {

    List<ApplicationUser> getAllUsers();
    ApplicationUser getUserByUserName(String userName);
}
