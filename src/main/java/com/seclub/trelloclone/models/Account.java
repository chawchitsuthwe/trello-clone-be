package com.seclub.trelloclone.models;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "account")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Account extends MainModel{

    @Id
    private String username;
    private String email;
    private String name;
    @Temporal(TemporalType.DATE)
    private Date dob;
    private Short verified = 0;

    @ManyToMany(mappedBy = "accounts")
    @JsonIgnore
    private List<Card> cards;

    public Account(){}
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getDob() {
        return dob;
    }
    public void setDob(Date dob) {
        this.dob = dob;
    }
    public Short getVerified() {
        return verified;
    }
    public void setVerified(Short verified) {
        this.verified = verified;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}