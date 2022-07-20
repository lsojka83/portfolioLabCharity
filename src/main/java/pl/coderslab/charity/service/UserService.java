package pl.coderslab.charity.service;

import pl.coderslab.charity.entity.User;

public interface UserService{

    User findByEmail(String email);

    User findById(Long id);

    void saveUser(User user);

    void updateUser(User user);
    void updateUser(User user, boolean updatePassword);
}
