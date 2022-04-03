package com.Dark.Creditcardmanagementsystem.Service.impl;

import com.Dark.Creditcardmanagementsystem.Exception.UserNotFoundException;
import com.Dark.Creditcardmanagementsystem.Repository.AccountRepository;
import com.Dark.Creditcardmanagementsystem.Repository.DebitTransactionRepository;
import com.Dark.Creditcardmanagementsystem.Repository.RefundRepository;
import com.Dark.Creditcardmanagementsystem.Repository.UserRepository;
import com.Dark.Creditcardmanagementsystem.Service.RefundService;
import com.Dark.Creditcardmanagementsystem.enums.Status;
import com.Dark.Creditcardmanagementsystem.model.*;
import com.Dark.Creditcardmanagementsystem.model.Request.TransactionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
@Service
public class RefundServiceImpl implements RefundService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private DebitTransactionRepository debitRepository;
    @Autowired
    private RefundRepository refundRepository;
    @Autowired
    private RepaymentServiceImpl repaymentService;


    @Override
    @Transactional
    public Refund processRefund(Long userId, TransactionRequest request) {
        Optional<User> user=userRepository.findById(userId);
        if(!user.isPresent()){
            throw new UserNotFoundException("user not present with Id"+ userId);
        }
        User savedUser=user.get();
        Account account=savedUser.getAccount();
        DebitTransaction debit=debitRepository.findTop1ByOrderId(request.getOrderId());

        if(debit==null){
            throw new RuntimeException("not a valid txn ID");
        }
        int amountToRefund=debit.getAmount();
        Refund refund=Refund.builder().account(account).status(Status.SUCCESS)
                      .transactionId(request.getOrderId()).build();

        Repayment repay=repaymentService.processRepayment(userId,request);
        if(repay.getStatus()!=Status.SUCCESS){
            refund.setStatus(Status.FAILURE);
            refundRepository.save(refund);
            throw new RuntimeException("Failure while refunding amount");
        }
        refundRepository.save(refund);
        return refund;
    }
}
