package com.Dark.Creditcardmanagementsystem.Controller;

import com.Dark.Creditcardmanagementsystem.Service.RepaymentService;
import com.Dark.Creditcardmanagementsystem.model.Repayment;
import com.Dark.Creditcardmanagementsystem.model.Request.TransactionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RepaymentController {
    @Autowired
    private RepaymentService repaymentService;
    @PostMapping("/user/{userId}/repay")
    public ResponseEntity<Repayment> processRepayment(@PathVariable("userId") Long userId,
                                                      @RequestBody TransactionRequest request){
        Repayment result=repaymentService.processRepayment(userId,request);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
