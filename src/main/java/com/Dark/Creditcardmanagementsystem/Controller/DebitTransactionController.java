package com.Dark.Creditcardmanagementsystem.Controller;

import com.Dark.Creditcardmanagementsystem.Service.DebitTransactionService;
import com.Dark.Creditcardmanagementsystem.model.DebitTransaction;
import com.Dark.Creditcardmanagementsystem.model.Request.TransactionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DebitTransactionController {
    @Autowired
    private DebitTransactionService debitTransactionService;
    @PostMapping("/user/{userId}/debit")
    public ResponseEntity<DebitTransaction> processDebit(@PathVariable("userId")Long userId,
                                                        @RequestBody TransactionRequest request){
        DebitTransaction result=debitTransactionService.processDebitTransaction(request,userId);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }
}
