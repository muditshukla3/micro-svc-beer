package com.ms.controller;

import com.ms.domain.BeerDTO;
import com.ms.domain.BeerPagedList;
import com.ms.domain.BeerStyleEnum;
import com.ms.services.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    private static final Integer DEFAULT_PAGE_NUMBER=0;
    private static final Integer DEFAULT_PAGE_SIZE=25;

    @Autowired
    BeerService beerService;

    @GetMapping(produces = {"application/json"})
    public ResponseEntity<BeerPagedList> listBeers(@RequestParam(value="pageNumber", required = false) Integer pageNumber,
                                                   @RequestParam(value="pageSize", required = false) Integer pageSize,
                                                   @RequestParam(value="beerName", required = false) String beerName,
                                                   @RequestParam(value="beerStyle", required = false) BeerStyleEnum beerStyle,
                                                   @RequestParam(value="showInventoryOnHand", required = false) Boolean showInventoryOnHand){

    if(showInventoryOnHand==null){
        showInventoryOnHand=false;
    }
    if(pageNumber==null || pageNumber<0){
        pageNumber=DEFAULT_PAGE_NUMBER;
    }

    if(pageSize==null || pageSize<1){
        pageSize=DEFAULT_PAGE_SIZE;
    }

    BeerPagedList beerPagedList = beerService.listBeers(beerName,beerStyle, PageRequest.of(pageNumber,pageSize),showInventoryOnHand);

    return new ResponseEntity<>(beerPagedList,HttpStatus.OK);

    }
    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDTO> getBeerById(@PathVariable UUID beerId,
                                               @RequestParam(value="showInventoryOnHand", required = false) boolean showInventoryOnHand){
        return new ResponseEntity<>(beerService.getBeerById(beerId,showInventoryOnHand), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BeerDTO> saveBeer(@Validated @RequestBody BeerDTO beerDTO){

        return new ResponseEntity<>(beerService.saveMyBeer(beerDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<BeerDTO> updateBeer(@PathVariable UUID beerId,@Valid @RequestBody BeerDTO beerDTO){

        return new ResponseEntity<>(beerService.updateMyBeer(beerId,beerDTO), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/upc/{beerUPC}")
    public ResponseEntity<BeerDTO> getBeerByUPC(@PathVariable String beerUPC){
        return new ResponseEntity<>(beerService.getBeerByUpc(beerUPC), HttpStatus.OK);
    }

}
