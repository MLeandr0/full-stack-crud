package study.crudback.service;

import study.crudback.model.*;

public interface CardService {

    Card save(Card card);
    
    void deleteById(Long id);
}
