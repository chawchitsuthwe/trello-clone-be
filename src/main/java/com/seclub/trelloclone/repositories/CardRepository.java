package com.seclub.trelloclone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seclub.trelloclone.models.Card;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long>{

}