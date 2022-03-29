package com.Dark.Creditcardmanagementsystem.Service.impl;

import com.Dark.Creditcardmanagementsystem.Exception.UserNotFoundException;
import com.Dark.Creditcardmanagementsystem.Repository.AccountRepository;
import com.Dark.Creditcardmanagementsystem.Repository.DebitTransactionRepository;
import com.Dark.Creditcardmanagementsystem.Repository.UserRepository;
import com.Dark.Creditcardmanagementsystem.Service.DebitTransactionService;
import com.Dark.Creditcardmanagementsystem.enums.Status;
import com.Dark.Creditcardmanagementsystem.model.Account;
import com.Dark.Creditcardmanagementsystem.model.Converter.DebitTxnConverter;
import com.Dark.Creditcardmanagementsystem.model.DebitTransaction;
import com.Dark.Creditcardmanagementsystem.model.Request.TransactionRequest;
import com.Dark.Creditcardmanagementsystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
public class DebitTransactionServiceImpl implements DebitTransactionService {

    private UserRepository userRepository;
    private AccountRepository accountRepository;
    private DebitTransactionRepository debitRepository;
    private DebitTxnConverter debitConverter;
    @Autowired
    public DebitTransactionServiceImpl(UserRepository userRepository,AccountRepository accountRepository,
                                       DebitTransactionRepository debitRepository,
                                       DebitTxnConverter debitConverter){
        this.userRepository=userRepository;
        this.accountRepository=accountRepository;
        this.debitConverter=debitConverter;
        this.debitRepository=debitRepository;
    }

    @Override
    @Transactional
    public DebitTransaction processDebitTransaction(TransactionRequest request, Long userId) {
        Optional<User> saveduser=userRepository.findById(userId);
        if(!saveduser.isPresent()){
            throw new UserNotFoundException("user not present exception with id -- "+ userId);
        }
        User user=saveduser.get();
        if(user.getAccount()==null){
            throw new RuntimeException("Account not exist for user");
        }
        Optional<Account> accfromDb=accountRepository.findById(user.getAccount().getAccountId());
        Account acc=accfromDb.get();
        DebitTransaction debitObj=debitConverter.dtoToObject(request);
        int amt=debitObj.getAmount();
        if(isValid(amt,acc.getAvailableCreditLimit())){
            debitObj.setStatus(Status.SUCCESS);
           int newAmount= acc.getAvailableCreditLimit()-amt;
           acc.setAvailableCreditLimit(newAmount);
        }
        else{
           debitObj.setStatus(Status.FAILURE);
            DebitTransaction result=debitRepository.save(debitObj);
           throw new RuntimeException("debit amount exceeded");
        }
        accountRepository.save(acc);
        debitObj.setAccount(acc);
        DebitTransaction result=debitRepository.save(debitObj);

//        System.out.println(acc.toString());
        return result;
    }

    @Override
    public List<DebitTransaction> getAllDebitTransactions(Long accountId) {

        return null;
    }

    private boolean isValid(int amt, int availableCreditLimit) {
        return availableCreditLimit>=amt;
    }
}
