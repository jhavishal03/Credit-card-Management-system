package com.Dark.Creditcardmanagementsystem.model.Converter;

import com.Dark.Creditcardmanagementsystem.model.Request.UserRequest;
import com.Dark.Creditcardmanagementsystem.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public User dtoToUser(UserRequest userRequest){
        User user=User.builder().firstName(userRequest.getFirstName()).
                     lastName(userRequest.getLastName()).emailId(userRequest.getEmailId()).
                       address(userRequest.getAddress()).contactNo(userRequest.getContactNo()).build();
        return user;
    }
}
