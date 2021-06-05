package com.ms.domain.events;

import com.ms.domain.BeerDTO;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BrewBeerEvent extends BeerEvent {

    public BrewBeerEvent(BeerDTO beerDto) {
        super(beerDto);
    }
}
