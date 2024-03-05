package study.crudback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.crudback.model.*;

import study.crudback.repository.CardRepository;

@Service
public class CardServiceImplementation implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'deleteCard'");

        cardRepository.deleteById(id);
    }

    @Override
    public Card save(Card card) {
        return cardRepository.save(card);
    }
    
}
