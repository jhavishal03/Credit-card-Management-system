package com.Dark.Creditcardmanagementsystem.Service.impl;

import com.Dark.Creditcardmanagementsystem.Exception.UserNotFoundException;
import com.Dark.Creditcardmanagementsystem.Repository.AccountRepository;
import com.Dark.Creditcardmanagementsystem.Repository.DebitTransactionRepository;
import com.Dark.Creditcardmanagementsystem.Repository.RefundRepository;
import com.Dark.Creditcardmanagementsystem.Repository.UserRepository;
import com.Dark.Creditcardmanagementsystem.Service.RefundService;
import com.Dark.Creditcardmanagementsystem.enums.Status;
import com.Dark.Creditcardmanagementsystem.model.Account;
import com.Dark.Creditcardmanagementsystem.model.DebitTransaction;
import com.Dark.Creditcardmanagementsystem.model.Refund;
import com.Dark.Creditcardmanagementsystem.model.Request.TransactionRequest;
import com.Dark.Creditcardmanagementsystem.model.User;

import java.util.Optional;

public class RefundServiceImpl implements RefundService {
    private UserRepository userRepository;
    private AccountRepository accountRepository;
    private DebitTransactionRepository debitRepository;
    private RefundRepository refundRepository;

    @Override
    //to be implemented later
    public Refund processRefund(Long userId, TransactionRequest request) {
        Optional<User> user=userRepository.findById(userId);
        if(!user.isPresent()){
            throw new UserNotFoundException("user not present with Id"+ userId);
        }
        User savedUser=user.get();
        Account account=savedUser.getAccount();
        DebitTransaction debit=debitRepository.findTop1ByOrderId(request.getOrderId());

        if(debit.getStatus()== Status.SUCCESS){

        }
        int amountToRefund=debit.getAmount();

        return null;
    }
}
