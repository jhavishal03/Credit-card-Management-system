package com.Dark.Creditcardmanagementsystem.Service;

import com.Dark.Creditcardmanagementsystem.model.Repayment;
import com.Dark.Creditcardmanagementsystem.model.Request.TransactionRequest;


public interface RepaymentService {
    public Repayment processRepayment(Long userId, TransactionRequest request);
}
