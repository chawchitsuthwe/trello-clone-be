package com.seclub.trelloclone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seclub.trelloclone.models.Card;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long>{

    @Query("select c from card c order by list_id asc, position asc")
    public java.util.List<Card> queryByListIdAscPositionAsc();
}