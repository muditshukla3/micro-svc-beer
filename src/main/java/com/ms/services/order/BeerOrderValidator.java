package com.ms.services.order;

import com.ms.domain.events.BeerOrderDto;
import com.ms.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
@RequiredArgsConstructor
@Slf4j
public class BeerOrderValidator  {

    private final BeerRepository beerRepository;

    public Boolean validateOrder(BeerOrderDto beerOrderDto){

        AtomicInteger beerNotFound = new AtomicInteger();
        beerOrderDto.getBeerOrderLines().forEach(orderLine -> {
            if(beerRepository.findByUpc(orderLine.getUpc())==null){
                beerNotFound.incrementAndGet();
            }
        });

        return beerNotFound.get() == 0;
    }
}
