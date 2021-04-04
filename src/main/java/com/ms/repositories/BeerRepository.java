package com.ms.repositories;

import com.ms.domain.Beer;
import com.ms.web.model.BeerStyleEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.UUID;

public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {
    Page<Beer> findAllByBeerNameAndBeerStyle(String beerName, BeerStyleEnum beerStyle, Pageable pageRequest);

    Page<Beer> findAllByBeerName(String beerName, Pageable pageRequest);

    Page<Beer> findAllByBeerStyle(BeerStyleEnum beerStyle, Pageable pageRequest);

    Optional<Beer> findByBeerId(UUID beerId);
}
