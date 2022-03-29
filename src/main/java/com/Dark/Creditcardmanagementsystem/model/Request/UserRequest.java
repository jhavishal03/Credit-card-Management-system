package com.Dark.Creditcardmanagementsystem.model.Request;

import com.Dark.Creditcardmanagementsystem.model.Address;
import lombok.Data;



@Data
public class UserRequest {
    private String firstName;
    private String lastName;
    private String emailId;
    private String contactNo;
    private Address address;
}
