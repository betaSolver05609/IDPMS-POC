package com.example.idpms.Model;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterResponseDTO {
    private BigInteger prime;
    private BigInteger generator;
    private String username;
    private BigInteger x;
    private BigInteger b;
    private BigInteger k;
}
