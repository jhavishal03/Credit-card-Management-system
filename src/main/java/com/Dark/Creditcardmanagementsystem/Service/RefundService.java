package com.Dark.Creditcardmanagementsystem.Service;

import com.Dark.Creditcardmanagementsystem.model.Refund;
import com.Dark.Creditcardmanagementsystem.model.Request.TransactionRequest;

public interface RefundService {
    public Refund processRefund(Long userId, TransactionRequest request);
}
