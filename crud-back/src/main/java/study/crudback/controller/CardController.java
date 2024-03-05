package study.crudback.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.crudback.model.*;
import study.crudback.service.CardService;

@RestController
@RequestMapping("/cards")
public class CardController {

    private CardService cardService;

    @GetMapping
    public Card greeting(){
        return new Card();
    }

    @PostMapping
    public ResponseEntity<Card> create(@RequestBody Card card){
        return ResponseEntity.status(HttpStatus.CREATED).body(cardService.save(card));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        cardService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
