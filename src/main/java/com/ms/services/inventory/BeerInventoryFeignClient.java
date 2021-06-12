package com.ms.services.inventory;

import com.ms.services.inventory.model.BeerInventoryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.UUID;

@FeignClient(name="beer-inventory")
public interface BeerInventoryFeignClient{

    @RequestMapping(method = RequestMethod.GET, value=BeerInventoryServiceRetTemplateImpl.INVENTORY_PATH)
    ResponseEntity<List<BeerInventoryDTO>> getOnHandInventory(@PathVariable  UUID beerId);
}
