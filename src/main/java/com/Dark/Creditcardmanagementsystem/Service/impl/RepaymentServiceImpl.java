package com.Dark.Creditcardmanagementsystem.Service.impl;

import com.Dark.Creditcardmanagementsystem.Exception.UserNotFoundException;
import com.Dark.Creditcardmanagementsystem.Repository.AccountRepository;
import com.Dark.Creditcardmanagementsystem.Repository.RepaymentRepository;
import com.Dark.Creditcardmanagementsystem.Repository.UserRepository;
import com.Dark.Creditcardmanagementsystem.Service.RepaymentService;
import com.Dark.Creditcardmanagementsystem.enums.Status;
import com.Dark.Creditcardmanagementsystem.model.Account;
import com.Dark.Creditcardmanagementsystem.model.Repayment;
import com.Dark.Creditcardmanagementsystem.model.Request.TransactionRequest;
import com.Dark.Creditcardmanagementsystem.model.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class RepaymentServiceImpl implements RepaymentService {
    private RepaymentRepository repaymentRepository;
    private UserRepository userRepository;
    private AccountRepository accountRepository;
    RepaymentServiceImpl(RepaymentRepository repaymentRepository,UserRepository userRepository,
                         AccountRepository accountRepository){
        this.userRepository=userRepository;
        this.repaymentRepository=repaymentRepository;
        this.accountRepository=accountRepository;
    }

    @Override
    @Transactional
    public Repayment processRepayment(Long userId, TransactionRequest request) {
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
        Repayment repayObj=Repayment.builder().orderId(request.getOrderId()).
                          amount(request.getAmount()).build();
        int amt=request.getAmount();
            repayObj.setStatus(Status.SUCCESS);
            int newAmount= acc.getAvailableCreditLimit()+amt;
            acc.setAvailableCreditLimit(newAmount);
        accountRepository.save(acc);
        repayObj.setAccount(acc);
        Repayment result=repaymentRepository.save(repayObj);
        return result;
    }
}
