package com.Dark.Creditcardmanagementsystem.Service.impl;

import com.Dark.Creditcardmanagementsystem.Exception.UserNotFoundException;
import com.Dark.Creditcardmanagementsystem.Repository.AccountRepository;
import com.Dark.Creditcardmanagementsystem.Service.AccountService;
import com.Dark.Creditcardmanagementsystem.Service.UserService;
import com.Dark.Creditcardmanagementsystem.model.Account;
import com.Dark.Creditcardmanagementsystem.model.Converter.AccountConverter;
import com.Dark.Creditcardmanagementsystem.model.Request.AccountRequest;
import com.Dark.Creditcardmanagementsystem.model.Response.AccountResponse;
import com.Dark.Creditcardmanagementsystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;
    private UserService userService;
    private AccountConverter accountConverter;
    @Autowired
    public AccountServiceImpl(AccountConverter accountConverter,AccountRepository accountRepository,
                              UserService userService){
        this.accountRepository=accountRepository;
        this.userService=userService;
        this.accountConverter=accountConverter;
    }
    @Override
    public AccountResponse saveAccountDetails(AccountRequest accountRequest, Long userId) {
        //check previous accounts
        User user=userService.getUserById(userId);
        Account acc=accountConverter.dtoToAccount(accountRequest);
        acc.setUser(user);
        Account savedacc=accountRepository.save(acc);
        return AccountResponse.builder().accountId(savedacc.getAccountId())
                .userName(user.getFirstName()+" "+user.getLastName()).
                        userEmail(user.getEmailId()).dateAccountCreated(savedacc.getDateAccountCreated())
                .authorisedLimit(savedacc.getAuthorisedCreditLimit()).build();
    }
    @Override
    public Account getAccountDetailforUser(Long userId){
        Optional<Account> account=accountRepository.findByUserId(userId);
      return account.get();
    }

    @Override
    @Transactional
    public Account updateAccountLimit(Long accountId, AccountRequest request) {
        Optional<Account> account=accountRepository.findById(accountId);
        if(!account.isPresent()){
            throw new UserNotFoundException("account not found with id  "+accountId);
        }
        Account accToUpdate=accountConverter.dtoToAccount(request);
        int diff=request.getAuthorisedCreditLimit()-account.get().getAuthorisedCreditLimit();
        int newAvailLimit=account.get().getAvailableCreditLimit()+diff;
        accToUpdate.setAvailableCreditLimit(newAvailLimit);
        accToUpdate.setAccountId(account.get().getAccountId());
        accToUpdate.setDateAccountCreated(account.get().getDateAccountCreated());
        accToUpdate.setUser(account.get().getUser());
        Account savedacc=accountRepository.save(accToUpdate);
        return savedacc;
    }
    @Override
    public List<Account> getAllAccounts(){
      return accountRepository.findAll();
    }
}
