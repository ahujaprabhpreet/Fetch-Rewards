package com.fetchrewards.exercise.repository;

import com.fetchrewards.exercise.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
}

