package com.ms.web.services;

import com.ms.web.model.BeerDTO;

import java.util.UUID;

public interface BeerService {

    BeerDTO getBeerById(UUID beerId);

    BeerDTO saveMyBeer(BeerDTO beerDTO);

    BeerDTO updateMyBeer(UUID beerId, BeerDTO beerDTO);
}
