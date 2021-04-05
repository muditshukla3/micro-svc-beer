package com.ms.web.services;

import com.ms.web.model.BeerDTO;
import com.ms.web.model.BeerPagedList;
import com.ms.web.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {

    BeerDTO getBeerById(UUID beerId, Boolean showInventoryOnHand);

    BeerDTO saveMyBeer(BeerDTO beerDTO);

    BeerDTO updateMyBeer(UUID beerId, BeerDTO beerDTO);

    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand);

    BeerDTO getBeerByUpc(String beerUPC);
}
