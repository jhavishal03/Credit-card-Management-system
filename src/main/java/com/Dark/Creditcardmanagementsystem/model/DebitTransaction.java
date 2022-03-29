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
@Table(
        uniqueConstraints = @UniqueConstraint(
                name = "unique_orderId",
                columnNames = "orderId"
        ))
public class DebitTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;
    @Column(unique = true)
    private String orderId;
    @Min(0)
    private int amount;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfTransaction;

    private Status status;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="accountID")
    private Account account;
}
