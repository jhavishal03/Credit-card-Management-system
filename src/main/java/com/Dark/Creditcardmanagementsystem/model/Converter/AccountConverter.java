package com.Dark.Creditcardmanagementsystem.model.Converter;

import com.Dark.Creditcardmanagementsystem.model.Account;
import com.Dark.Creditcardmanagementsystem.model.Request.AccountRequest;
import org.springframework.stereotype.Component;

@Component
public class AccountConverter {
    public Account dtoToAccount(AccountRequest accountReq){
      return Account.builder().authorisedCreditLimit(accountReq.getAuthorisedCreditLimit()).
                availableCreditLimit(accountReq.getAuthorisedCreditLimit()).build();
    }
}
