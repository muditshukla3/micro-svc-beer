package com.ms.services.inventory;

import com.ms.services.inventory.model.BeerInventoryDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Profile("!local")
@Slf4j
@Component
public class BeerInventoryServiceRetTemplateImpl implements BeerInventoryService {

    public static final String INVENTORY_PATH="/api/v1/beer/{beerId}/inventory";

    @Autowired
    RestTemplate restTemplate;

    public void setBeerInventoryHost(String beerInventoryHost) {
        this.beerInventoryHost = beerInventoryHost;
    }

    @Value("${beer.svc.host.inventory}")
    private String beerInventoryHost;


    @Override
    public Integer getOnHandInventory(UUID beerId) {

        ResponseEntity<List<BeerInventoryDTO>> responseEntity = restTemplate.exchange(
                beerInventoryHost + INVENTORY_PATH, HttpMethod.GET, null
                , new ParameterizedTypeReference<List<BeerInventoryDTO>>() {},(Object)beerId
        );

        Integer quantityOnHand = Objects.requireNonNull(responseEntity.getBody())
                .stream().mapToInt(BeerInventoryDTO::getQuantityOnHand).sum();
        return quantityOnHand;
    }
}
