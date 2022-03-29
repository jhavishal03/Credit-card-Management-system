package com.Dark.Creditcardmanagementsystem.model;

import com.Dark.Creditcardmanagementsystem.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Repayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long repaymentTransactionId;
    @Min(0)
    private int amount;
    @Column(unique = true,nullable = false)
    private String orderId;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;
     private Status status;
     @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
     @JoinColumn(name = "accountId")
     private Account account;
}
