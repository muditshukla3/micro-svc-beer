package com.ms.mapper;

import com.ms.domain.Beer;
import com.ms.domain.BeerDTO;
import com.ms.web.services.inventory.BeerInventoryService;
import org.springframework.beans.factory.annotation.Autowired;

public class BeerMapperDecorator  implements BeerMapper{

    private BeerInventoryService beerInventoryService;
    private BeerMapper beerMapper;

    @Autowired
    public void setBeerInventoryService(BeerInventoryService beerInventoryService) {
        this.beerInventoryService = beerInventoryService;
    }

    @Autowired
    public void setBeerMapper(BeerMapper beerMapper) {
        this.beerMapper = beerMapper;
    }

    @Override
    public BeerDTO beerToBeerDTO(Beer beer) {
        return beerMapper.beerToBeerDTO(beer);
    }

    @Override
    public BeerDTO beerToBeerDTOWithInventory(Beer beer) {
        BeerDTO beerDTO = beerMapper.beerToBeerDTO(beer);
        beerDTO.setQuantityOnHand(beerInventoryService.getOnHandInventory(beer.getBeerId()));
        return beerDTO;
    }

    @Override
    public Beer beerDTOToBeer(BeerDTO beerDTO) {
        return beerMapper.beerDTOToBeer(beerDTO);
    }
}
