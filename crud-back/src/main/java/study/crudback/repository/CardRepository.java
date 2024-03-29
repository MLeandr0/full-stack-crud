package study.crudback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import study.crudback.model.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {}
