package com.Dark.Creditcardmanagementsystem.Controller;

import com.Dark.Creditcardmanagementsystem.Service.DebitTransactionService;
import com.Dark.Creditcardmanagementsystem.model.DebitTransaction;
import com.Dark.Creditcardmanagementsystem.model.Request.TransactionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DebitTransactionController {
    @Autowired
    private DebitTransactionService debitTransactionService;
    @PostMapping("/user/{userId}/debit")
    public ResponseEntity<DebitTransaction> processDebit(@PathVariable("userId") Long userId,
                                                        @RequestBody TransactionRequest request){
        DebitTransaction result=debitTransactionService.processDebitTransaction(request,userId);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @GetMapping("user/{userId}/debit")
    public ResponseEntity<List<DebitTransaction>> getAllDebitTransactions(@PathVariable("userId")Long userId){
      List<DebitTransaction> result=debitTransactionService.getAllDebitTransactions(userId);
      return new ResponseEntity<>(result,HttpStatus.OK);
    }
    @GetMapping("user/{userId}/debit/{orderId}")
    public ResponseEntity<DebitTransaction> getDebitTransactionsById(@PathVariable("userId")Long userId,@PathVariable("orderId") String orderId){
        DebitTransaction result=debitTransactionService.getTransactionByOrderId(userId,orderId);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
