package com.example.idpms.Model;

import java.math.BigInteger;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NamedQueries(value={
    @NamedQuery(name="fetch_user_by_username", query="select u from Users u where u.username=:username")
})
@NoArgsConstructor
@Entity
@Table(name="USERS")
@AllArgsConstructor
@Getter
@Setter
public class Users {

    @Id
    @GeneratedValue
    private long id;

    @Column(name="username", nullable=false)
    private String username;

    @Column(name="g", nullable = false, columnDefinition = "NUMERIC")
    private BigInteger generator;

    @Column(name="N", nullable = false, columnDefinition = "NUMERIC")
    private BigInteger prime;

    @Column(name="x", nullable = false, columnDefinition = "NUMERIC")
    private BigInteger x;

    @Column(name="b", nullable = false, columnDefinition = "NUMERIC")
    private BigInteger b;

    @Column(name="k", nullable = false, columnDefinition = "NUMERIC")
    private BigInteger k;

    public Users(String username, BigInteger g, BigInteger N, BigInteger x, BigInteger b, BigInteger k) {
        this.username = username;
        this.generator=g;
        this.prime=N;
        this.x=x;
        this.b=b;
        this.k=k;
    }

}
