package com.Dark.Creditcardmanagementsystem.Controller;

import com.Dark.Creditcardmanagementsystem.Service.AccountService;
import com.Dark.Creditcardmanagementsystem.model.Account;
import com.Dark.Creditcardmanagementsystem.model.Request.AccountRequest;
import com.Dark.Creditcardmanagementsystem.model.Response.AccountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    private AccountService accountService;
    @Autowired
    public AccountController(AccountService accountService){
        this.accountService=accountService;
    }
    @PostMapping("user/{userId}/account")
    public ResponseEntity<AccountResponse> createAccount(@PathVariable("userId") Long userId,
                                        @RequestBody AccountRequest account){
        AccountResponse savedAccount=accountService.saveAccountDetails(account,userId);
        return new ResponseEntity<>(savedAccount, HttpStatus.CREATED);
    }
    @GetMapping("/user/{userId}/account")
    public ResponseEntity<Account> getAccountForUser(@PathVariable("userId") Long userId){
        return new ResponseEntity<>(accountService.getAccountDetailforUser(userId),HttpStatus.OK);
    }

    @PutMapping("account/{accountId}")
    public ResponseEntity<Account> updateLimitOfAccount(@PathVariable("accountId") Long accountId,@RequestBody AccountRequest accountRequest){
     Account res=accountService.updateAccountLimit(accountId,accountRequest);
     return new ResponseEntity<>(res,HttpStatus.OK);
    }

}
