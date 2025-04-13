package com.example.idpms.Model;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AuthenticationDTO {
    private String username;
    private BigInteger client_key;
    private BigInteger A;
}
