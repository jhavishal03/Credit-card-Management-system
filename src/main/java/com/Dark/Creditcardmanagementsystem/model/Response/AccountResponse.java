package com.Dark.Creditcardmanagementsystem.model.Response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class AccountResponse {
 public Long accountId;
 public String userName;
 public String userEmail;
 public String userContact;
 public int authorisedLimit;
 public Date dateAccountCreated;
}
