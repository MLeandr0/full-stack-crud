package study.crudback.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long cardId;
    private String cardName;
    private int cardCost;
    private int cardDefense;
    private int cardLife;

    @ManyToOne@JoinColumn(name = "deck_id")
    private Deck deck;
}
