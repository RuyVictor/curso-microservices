package com.cursomicroservice.mscartoes.application;

import com.cursomicroservice.mscartoes.application.dtos.GetCardsByClientDTO;
import com.cursomicroservice.mscartoes.domain.ClientCard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientCardService {
    private final ClientCardRepository clientCardRepository;

    public List<GetCardsByClientDTO.Output> getCardsByCustomer(String cpf) {
        List<ClientCard> clientCards = clientCardRepository.findByCpf(cpf);
        return clientCards.stream().map(GetCardsByClientDTO.Output::fromModel).collect(Collectors.toList());
    }
}
