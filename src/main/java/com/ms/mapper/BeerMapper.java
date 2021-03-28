package com.ms.mapper;

import com.ms.domain.Beer;
import com.ms.web.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

    BeerDTO beerToBeerDTO(Beer beer);

    Beer beerDTOToBeer(BeerDTO beerDTO);
}
