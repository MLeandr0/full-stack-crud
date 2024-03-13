package study.crudback.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Deck {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long deckId;
    private String deckName;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Card> cards = new ArrayList<>();
}
