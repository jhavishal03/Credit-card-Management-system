package com.Dark.Creditcardmanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String firstName;
    private String lastName;
    @Column(unique = true,updatable = false)
    private String emailId;
    private String contactNo;
    @Embedded
    private Address address;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "user")
    private Account account;
}
