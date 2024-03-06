package study.crudback.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import study.crudback.model.Deck;
import study.crudback.repository.DeckRepository;

@RestController
@RequestMapping("/decks")
public class DeckController {
    
    @Autowired
    private DeckRepository deckRepository;

    @PostMapping("/save-deck")
    public ResponseEntity<Deck> createDeck(@RequestBody Deck deck) {
        return ResponseEntity.status(HttpStatus.CREATED).body(deckRepository.save(deck));
    }

    @DeleteMapping("/delete-deck/{id}")
    public ResponseEntity<Deck> deleteDeck(@PathVariable Long id) {
        deckRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/update-deck/{id}")
    public ResponseEntity<Deck> update(@PathVariable("id") long id, @RequestBody Deck deck) {
        return ResponseEntity.status(HttpStatus.OK).body(deckRepository.save(deck));
    }

    @GetMapping("/get-decks")
    public ResponseEntity<List<Deck>> getAllDecks() {

        List<Deck> decks = new ArrayList<Deck>();
        deckRepository.findAll().forEach(decks::add);

        return new ResponseEntity<>(decks, HttpStatus.OK);
    }
}
