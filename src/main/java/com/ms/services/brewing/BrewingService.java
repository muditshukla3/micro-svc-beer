package com.ms.services.brewing;

import com.ms.config.JMSConfig;
import com.ms.domain.Beer;
import com.ms.domain.events.BrewBeerEvent;
import com.ms.mapper.BeerMapper;
import com.ms.repositories.BeerRepository;
import com.ms.services.inventory.BeerInventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BrewingService {

    private final BeerRepository beerRepository;
    private final BeerInventoryService beerInventoryService;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper beerMapper;

    @Scheduled(fixedRate = 5000)//every 5 second
    public void checkForLowInventory(){

        List<Beer> beers = beerRepository.findAll();

        beers.forEach(beer -> {
            Integer invOHand = beerInventoryService.getOnHandInventory(beer.getBeerId());
            log.info("Min On Hand is {}",beer.getMinOnHand());
            log.info("Inventory is {}",invOHand);

            if(beer.getMinOnHand() >= invOHand){
                jmsTemplate.convertAndSend(JMSConfig.BREWING_REQUEST_QUEUE,
                        new BrewBeerEvent(beerMapper.beerToBeerDTO(beer)));

            }
        });
    }

}
