package com.ms.mapper;

import com.ms.domain.Beer;
import com.ms.domain.BeerDTO;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {

    BeerDTO beerToBeerDTO(Beer beer);

    BeerDTO beerToBeerDTOWithInventory(Beer beer);

    Beer beerDTOToBeer(BeerDTO beerDTO);
}
