package com.seclub.trelloclone.repositories;

import com.seclub.trelloclone.models.Checklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChecklistRepository extends JpaRepository<Checklist, Long> {

    public Long deleteByCardId(Long cardId);
}
