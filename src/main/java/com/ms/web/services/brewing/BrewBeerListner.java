package com.ms.web.services.brewing;

import com.ms.config.config.JMSConfig;
import com.ms.domain.Beer;
import com.ms.events.BrewBeerEvent;
import com.ms.events.NewInventoryEvent;
import com.ms.repositories.BeerRepository;
import com.ms.web.model.BeerDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BrewBeerListner {

    private final BeerRepository beerRepository;
    private final JmsTemplate jmsTemplate;

    @Transactional
    @JmsListener(destination = JMSConfig.BREWING_REQUEST_QUEUE)
    public void listen(BrewBeerEvent brewBeerEvent){
        BeerDTO beerDTO  = brewBeerEvent.getBeerDTO();
        log.info("BeerDTO:::: {}",beerDTO);
        log.info("getting beer with id {}",beerDTO.getBeerId());
        Beer beer = beerRepository.findByBeerId(beerDTO.getBeerId()).get();
        beerDTO.setQuantityOnHand(beer.getQuantityToBrew());

        NewInventoryEvent newInventoryEvent = new NewInventoryEvent(beerDTO);
        log.info("Brewed Beer "+beer.getMinOnHand()+" QOH "+beerDTO.getQuantityOnHand());

        jmsTemplate.convertAndSend(JMSConfig.NEW_INVENTORY_QUEUE,newInventoryEvent);

    }
}
