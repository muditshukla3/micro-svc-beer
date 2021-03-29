package com.ms.web.services;

import com.ms.domain.Beer;
import com.ms.mapper.BeerMapper;
import com.ms.repositories.BeerRepository;
import com.ms.web.exception.NotFoundException;
import com.ms.web.model.BeerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BeerServiceImpl implements BeerService {
    @Autowired
    BeerRepository beerRepository;

    @Autowired
    BeerMapper beerMapper;

    @Override
    public BeerDTO getBeerById(UUID beerId) {
        return beerMapper.beerToBeerDTO(
                beerRepository.findById(beerId).orElseThrow(() -> new NotFoundException("BeerNotFound"))
        );
    }

    @Override
    public BeerDTO saveMyBeer(BeerDTO beerDTO) {

        return beerMapper.beerToBeerDTO(
                beerRepository.save(
                        beerMapper.beerDTOToBeer(beerDTO)
                ));
    }

    @Override
    public BeerDTO updateMyBeer(UUID beerId, BeerDTO beerDTO) {

        Beer beer = beerRepository.findById(beerId).orElseThrow(()-> new NotFoundException("BeerNotFound"));

        if(beer!=null){
            beerMapper.beerToBeerDTO(
                    beerRepository.save(
                            beerMapper.beerDTOToBeer(beerDTO)
                    ));
        }

        return null;
    }
}
