package study.crudback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import study.crudback.model.Deck;

@Repository
public interface DeckRepository extends JpaRepository<Deck, Long> {}
