package com.Dark.Creditcardmanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude="user")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    private int authorisedCreditLimit;
    private int availableCreditLimit;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAccountCreated;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAccountUpdated;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY
    )
    @JoinColumn(name = "userID",referencedColumnName = "userId")
    @JsonIgnore
    private User user;

}
