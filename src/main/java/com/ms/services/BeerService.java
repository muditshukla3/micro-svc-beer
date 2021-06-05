package com.ms.services;

import com.ms.domain.BeerDTO;
import com.ms.domain.BeerPagedList;
import com.ms.domain.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {

    BeerDTO getBeerById(UUID beerId, Boolean showInventoryOnHand);

    BeerDTO saveMyBeer(BeerDTO beerDTO);

    BeerDTO updateMyBeer(UUID beerId, BeerDTO beerDTO);

    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand);

    BeerDTO getBeerByUpc(String beerUPC);
}
