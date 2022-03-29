package com.Dark.Creditcardmanagementsystem.model;

import com.Dark.Creditcardmanagementsystem.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Refund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long refundId;

    private Long transactionId;
    @CreationTimestamp

    @Temporal(TemporalType.TIMESTAMP)
    private Date refundTransactionDate;

    private Status status;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="accountId")
    private Account account;

}
