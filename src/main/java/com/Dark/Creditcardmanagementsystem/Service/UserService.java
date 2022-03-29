package com.Dark.Creditcardmanagementsystem.Service;

import com.Dark.Creditcardmanagementsystem.model.Request.UserRequest;
import com.Dark.Creditcardmanagementsystem.model.User;

import java.util.List;

public interface UserService {
    public User addUser(UserRequest user);
    public List<User> getAllUsers();
    public User getUserById(Long id);
    public User getUserByEmail(String emailId);
    public List<User> getUsersByFirstName(String firstName);
    public List<User> getUsersByLastName(String lastName);
}
