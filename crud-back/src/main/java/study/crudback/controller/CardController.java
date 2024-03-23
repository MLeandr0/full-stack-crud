package study.crudback.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import study.crudback.model.Card;
import study.crudback.repository.CardRepository;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    private final CardRepository cardRepository;

    public CardController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Card>> getCard(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(cardRepository.findById(id));
    }

    @PostMapping
    public ResponseEntity<Card> createCard(@RequestBody Card card) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cardRepository.save(card));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Card> delete(@PathVariable Long id) {
        cardRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
