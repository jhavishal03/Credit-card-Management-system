package com.Dark.Creditcardmanagementsystem.model.Request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionRequest {
 public int amount;
 public String orderId;
}
