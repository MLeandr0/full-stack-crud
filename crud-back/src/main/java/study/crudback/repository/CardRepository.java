package study.crudback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.crudback.model.Card;


public interface CardRepository extends JpaRepository<Card, Long>{
    
}
