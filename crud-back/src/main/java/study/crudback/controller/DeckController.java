package study.crudback.controller;

import java.util.ArrayList;
import java.util.List;

import study.crudback.model.Deck;
import study.crudback.model.Card;
import study.crudback.repository.CardRepository;
import study.crudback.repository.DeckRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/decks")
public class DeckController {
    
    private final DeckRepository deckRepository;
    private final CardRepository cardRepository;

    public DeckController(DeckRepository deckRepository, CardRepository cardRepository) {
        this.deckRepository = deckRepository;
        this.cardRepository = cardRepository;
    }

    @PostMapping
    public ResponseEntity<Deck> createDeck(@RequestBody Deck deck) {
        return ResponseEntity.status(HttpStatus.CREATED).body(deckRepository.save(deck));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Deck> deleteDeck(@PathVariable Long id) {
        Deck deck = deckRepository.findById(id).orElse(null);

        if (deck == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        cardRepository.deleteAll(deck.getCards());
        deckRepository.deleteById(id);
    
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Deck> updateDeck(@PathVariable("id") Long id, @RequestBody Deck deck) {
        Deck savedDeck = deckRepository.findById(id).orElseThrow();
        savedDeck.setDeckName(deck.getDeckName());

        return ResponseEntity.status(HttpStatus.OK).body(deckRepository.save(savedDeck));
    }

    @GetMapping
    public ResponseEntity<List<Deck>> getAllDecks() {

        List<Deck> decks = new ArrayList<Deck>();
        deckRepository.findAll().forEach(decks::add);
        return new ResponseEntity<>(decks, HttpStatus.OK);
    }
    
    @PostMapping("/{deckId}/cards")
    public ResponseEntity<Card> addCardToDeck(@PathVariable Long deckId, @RequestBody Card card) {
        Deck deck = deckRepository.findById(deckId).orElseThrow();
        long cardId = card.getCardId();

        if (deck.getCards().stream().anyMatch(c -> c.getCardId() == cardId)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(card);
        }

        card.setDeck(deck);
        card = cardRepository.save(card);
        List<Card> cards = deck.getCards();
        cards.add(card);
        deck.setCards(cards);

        deckRepository.save(deck);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(card);
    }

    @GetMapping("/{deckId}/cards")
    public ResponseEntity<List<Card>> getCardsForDeck(@PathVariable Long deckId) {
        Deck deck = deckRepository.findById(deckId).orElseThrow();
        List<Card> cards = deck.getCards();
        return ResponseEntity.status(HttpStatus.OK).body(cards);
    }
    
    @DeleteMapping("/{deckId}/cards/{cardId}")
    public ResponseEntity<Void> deleteCardFromDeck(@PathVariable Long deckId, @PathVariable Long cardId) {
        Deck deck = deckRepository.findById(deckId).orElseThrow(() -> new RuntimeException("Deck not found"));
        Card cardToDelete = deck.getCards().stream()
                .filter(card -> card.getCardId().equals(cardId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Card not found in deck"));
    
        deck.getCards().remove(cardToDelete);
        deckRepository.save(deck);
        cardRepository.deleteById(cardId);
        return ResponseEntity.status(HttpStatus.OK).build();
    } 
}
