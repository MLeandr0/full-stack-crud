package study.crudback.model;

import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Deck {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long deckId;
    private String deckName;
    //private ArrayList<Card> cards = new ArrayList<>(30);

}
