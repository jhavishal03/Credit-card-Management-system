package com.Dark.Creditcardmanagementsystem.model.Converter;

import com.Dark.Creditcardmanagementsystem.model.DebitTransaction;
import com.Dark.Creditcardmanagementsystem.model.Request.TransactionRequest;
import org.springframework.stereotype.Component;

@Component
public class DebitTxnConverter {
 public DebitTransaction dtoToObject(TransactionRequest request){
     return  DebitTransaction.builder().
             orderId(request.getOrderId())
             .amount(request.getAmount()).build();
 }
}
