package com.Dark.Creditcardmanagementsystem.Service;

import com.Dark.Creditcardmanagementsystem.model.Account;
import com.Dark.Creditcardmanagementsystem.model.Request.AccountRequest;
import com.Dark.Creditcardmanagementsystem.model.Response.AccountResponse;

import java.util.List;

public interface AccountService {
    public AccountResponse saveAccountDetails(AccountRequest accountRequest, Long userId);
    public Account getAccountDetailforUser(Long userId);
    public Account updateAccountLimit(Long id,AccountRequest request);
    public List<Account> getAllAccounts();
}
