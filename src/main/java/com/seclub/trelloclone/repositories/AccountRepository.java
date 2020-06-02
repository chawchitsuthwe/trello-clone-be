package com.seclub.trelloclone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seclub.trelloclone.models.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

}
