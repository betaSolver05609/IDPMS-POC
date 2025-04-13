package com.example.idpms.Service;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.idpms.Model.RegisterResponseDTO;
import com.example.idpms.Model.Users;
import com.example.idpms.Repo.UserRepo;
import com.example.idpms.Utils.Utils;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    public RegisterResponseDTO processUserRegistration(String username, String password) throws Exception {
        BigInteger generator = Utils.generateRandomNumber();
        BigInteger prime = Utils.generatePrime(1024);
        BigInteger salt = Utils.generateRandomNumber();
        BigInteger x = Utils.H(salt, password);
        BigInteger b =Utils.generateRandomNumber();
        BigInteger k =Utils.generateRandomNumber();
        userRepo.persistUser(generator, prime, username, x, b, k);
        RegisterResponseDTO responseDTO = new RegisterResponseDTO(prime, generator, username,x,b,k);
        return responseDTO;
    }
    public boolean processUserAuthentication(String username, BigInteger client_key, BigInteger A) throws Exception {
        Users user = userRepo.fetchUserByUsername(username);
        BigInteger g = user.getGenerator();
        BigInteger N = user.getPrime();
        BigInteger x = user.getX();
        BigInteger v = g.modPow(x, N);
        BigInteger b = user.getB();
        BigInteger k = user.getK();
        BigInteger B = k.multiply(v).add(g.modPow(b, N)).mod(N);
        BigInteger u = Utils.H(A,B);
        BigInteger S_server = A.multiply(v.modPow(u, N)).modPow(b, N);
        BigInteger K_server = Utils.H(S_server);
        System.out.println(K_server);
        return K_server.equals(client_key);
    }
}
