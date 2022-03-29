package com.Dark.Creditcardmanagementsystem.model;

import lombok.*;

import javax.persistence.Embeddable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {
    private String street;
    private String city;
    private String state;
    private String pin;
}
