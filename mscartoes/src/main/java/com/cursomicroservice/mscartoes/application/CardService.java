package com.cursomicroservice.mscartoes.application;

import com.cursomicroservice.mscartoes.application.dtos.SaveCardDTO;
import com.cursomicroservice.mscartoes.domain.Card;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;

    @Transactional
    public SaveCardDTO.Output save(SaveCardDTO.Input input) {
        Card card = new Card(null, input.getName(), input.getBrand(), input.getIncome(), input.getCardLimit());
        Card savedCard = cardRepository.save(card);
        return new SaveCardDTO.Output(savedCard);
    }

    public List<Card> getCardsWithIncomeLessThanEqual(Long income) {
        var formattedIncome = BigDecimal.valueOf(income);
        return cardRepository.findByIncomeLessThanEqual(formattedIncome);
    }
}
