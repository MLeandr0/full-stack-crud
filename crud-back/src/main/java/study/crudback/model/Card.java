package study.crudback.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity
public class Card {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long cardId;
    private String cardName;
    private int cardMana;
    private int cardShield;
    private int cardLife;
}
