package com.ms.services.inventory;

import com.ms.services.inventory.model.BeerInventoryDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Profile("local")
@Service
@RequiredArgsConstructor
public class BeerInventoryServiceFeign implements BeerInventoryService {

    private final InventoryServiceFeignClient inventoryServiceFeignClient;

    @Override
    public Integer getOnHandInventory(UUID uuid) {
        log.info("Calling feign client::: for beer id {}",uuid);
        ResponseEntity<List<BeerInventoryDTO>> responseEntity = inventoryServiceFeignClient.getOnHandInventory(uuid);

        Integer quantityOnHand = Objects.requireNonNull(responseEntity.getBody())
                .stream()
                .mapToInt(BeerInventoryDTO::getQuantityOnHand)
                .sum();
        log.info("Got quantity on hand:::: {}",quantityOnHand);
        return quantityOnHand;
    }
}
