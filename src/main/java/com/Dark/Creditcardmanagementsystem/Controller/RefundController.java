package com.Dark.Creditcardmanagementsystem.Controller;

import com.Dark.Creditcardmanagementsystem.Service.impl.RefundServiceImpl;
import com.Dark.Creditcardmanagementsystem.model.Refund;
import com.Dark.Creditcardmanagementsystem.model.Request.TransactionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RefundController {
    @Autowired
    private RefundServiceImpl refundService;

    @PostMapping("/user/{userId}/refund")
    public ResponseEntity<Refund> processRefund(@PathVariable("userId") Long userId, @RequestBody TransactionRequest request){
        Refund refund=refundService.processRefund(userId,request);
        return new ResponseEntity<>(refund, HttpStatus.CREATED);
    }

}
