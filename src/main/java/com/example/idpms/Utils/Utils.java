package com.example.idpms.Utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;

public class Utils {

    public static final SecureRandom random = new SecureRandom();

    public static BigInteger generatePrime(int bitLength) {
        BigInteger prime = BigInteger.probablePrime(bitLength, random);    
        return prime;
    }

    public static BigInteger generateRandomNumber() {
        return new BigInteger(256, random);
    }

    public static BigInteger H(Object... vals) throws Exception {
        MessageDigest sha256=MessageDigest.getInstance("SHA-256");
        for(Object val:vals) {
            sha256.update(((BigInteger) val).toByteArray());
        }
        return new BigInteger(1, sha256.digest());
    }

}
