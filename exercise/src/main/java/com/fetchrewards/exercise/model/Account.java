package com.fetchrewards.exercise.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Account {

    public Account() {
    }

    @Id
    @Column(name="UUID")
    private UUID uuid=UUID.randomUUID();

    @Column(name="PayerName")
    private String payerName;

    @Column(name="Points")
    private int points;

    @Column(name="TransactionDate")
    @CreationTimestamp
    private LocalDateTime transactionDate;

    //Getters and Setters
    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }
}
