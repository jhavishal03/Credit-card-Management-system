package com.Dark.Creditcardmanagementsystem.Service;

import com.Dark.Creditcardmanagementsystem.model.DebitTransaction;
import com.Dark.Creditcardmanagementsystem.model.Request.TransactionRequest;

import java.util.List;

public interface DebitTransactionService {
    public DebitTransaction processDebitTransaction(TransactionRequest request, Long userId);
    public List<DebitTransaction> getAllDebitTransactions(Long accountId);
}
