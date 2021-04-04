package com.ms.web.services;

import com.ms.domain.Beer;
import com.ms.mapper.BeerMapper;
import com.ms.repositories.BeerRepository;
import com.ms.web.exception.NotFoundException;
import com.ms.web.model.BeerDTO;
import com.ms.web.model.BeerPagedList;
import com.ms.web.model.BeerStyleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BeerServiceImpl implements BeerService {
    @Autowired
    BeerRepository beerRepository;

    @Autowired
    BeerMapper beerMapper;

    @Override
    public BeerDTO getBeerById(UUID beerId, Boolean showInventoryOnHand) {

        if(showInventoryOnHand){
            return beerMapper.beerToBeerDTOWithInventory(
                    beerRepository.findByBeerId(beerId).orElseThrow(() -> new NotFoundException("BeerNotFound"))
            );
        }else{
            return beerMapper.beerToBeerDTO(
                    beerRepository.findByBeerId(beerId).orElseThrow(() -> new NotFoundException("BeerNotFound"))
            );
        }

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

    @Override
    public BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle,
                                   PageRequest pageRequest, Boolean showInventoryOnHand) {
        BeerPagedList beerPagedList;
        Page<Beer> beerPage;

        if (StringUtils.hasText(beerName) && StringUtils.hasText(beerStyle.toString())) {
            //search both
            beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
        } else if (StringUtils.hasText(beerName) && StringUtils.hasText(beerStyle.toString())) {
            //search beer_service name
            beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
        } else if (StringUtils.hasText(beerName) && StringUtils.hasText(beerStyle.toString())) {
            //search beer_service style
            beerPage = beerRepository.findAllByBeerStyle(beerStyle, pageRequest);
        } else {
            beerPage = beerRepository.findAll(pageRequest);
        }

        if(showInventoryOnHand){
            beerPagedList = new BeerPagedList(beerPage
                    .getContent()
                    .stream()
                    .map(beerMapper::beerToBeerDTOWithInventory)
                    .collect(Collectors.toList()),
                    PageRequest
                            .of(beerPage.getPageable().getPageNumber(),
                                    beerPage.getPageable().getPageSize()),
                    beerPage.getTotalElements());
        }else{
            beerPagedList = new BeerPagedList(beerPage
                    .getContent()
                    .stream()
                    .map(beerMapper::beerToBeerDTO)
                    .collect(Collectors.toList()),
                    PageRequest
                            .of(beerPage.getPageable().getPageNumber(),
                                    beerPage.getPageable().getPageSize()),
                    beerPage.getTotalElements());
        }


        return beerPagedList;
    }
}
