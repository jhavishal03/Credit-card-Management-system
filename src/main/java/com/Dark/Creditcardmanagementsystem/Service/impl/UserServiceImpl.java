package com.Dark.Creditcardmanagementsystem.Service.impl;

import com.Dark.Creditcardmanagementsystem.Exception.UserAlreadyPresentException;
import com.Dark.Creditcardmanagementsystem.Exception.UserNotFoundException;
import com.Dark.Creditcardmanagementsystem.Repository.AccountRepository;
import com.Dark.Creditcardmanagementsystem.Repository.UserRepository;
import com.Dark.Creditcardmanagementsystem.Service.UserService;
import com.Dark.Creditcardmanagementsystem.model.Converter.UserConverter;
import com.Dark.Creditcardmanagementsystem.model.Request.UserRequest;
import com.Dark.Creditcardmanagementsystem.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserConverter userConverter){
        this.userRepository=userRepository;
        this.userConverter=userConverter;
    }
    @Override
    public User addUser(UserRequest user){
        Optional<User> userSaved=userRepository.findByEmailId(user.getEmailId());
        if(userSaved.isPresent()){
            throw new UserAlreadyPresentException("user already present with this Email "+
                    "please use different emailId");
        }
        User newUser=userRepository.save(userConverter.dtoToUser(user));
        return newUser;
    }
    @Override
    public List<User> getAllUsers(){
        List<Sort.Order> orders=new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.ASC,"firstName"));
        orders.add(new Sort.Order(Sort.Direction.ASC,"lastName"));
        return userRepository.findAll(Sort.by(orders));
    }
    @Override
    public User getUserById(Long id){
        Optional<User> user=userRepository.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException("user not present with ID-- "+id);
        }
        return user.get();
    }
    @Override
    public User getUserByEmail(String emailId){
        Optional<User> user=userRepository.findByEmailId(emailId);
        if(!user.isPresent()){
            throw new UserNotFoundException("user not present with email --> "+ emailId);
        }
        return user.get();
    }
    @Override
    public List<User> getUsersByFirstName(String firstName){
        List<User> savedUsers=userRepository.findByFirstNameIgnoreCase(firstName);
        return savedUsers;
    }
    @Override
    public List<User> getUsersByLastName(String firstName){
        List<User> savedUsers=userRepository.findByLastNameIgnoreCase(firstName);
        return savedUsers;
    }
}
