package com.ms.web.controller;

import com.ms.web.model.BeerDTO;
import com.ms.web.services.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    @Autowired
    BeerService beerService;

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDTO> getBeerById(@PathVariable UUID beerId){
        return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BeerDTO> saveBeer(@Validated @RequestBody BeerDTO beerDTO){

        return new ResponseEntity<>(beerService.saveMyBeer(beerDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<BeerDTO> updateBeer(@PathVariable UUID beerId,@Valid @RequestBody BeerDTO beerDTO){

        return new ResponseEntity<>(beerService.updateMyBeer(beerId,beerDTO), HttpStatus.NO_CONTENT);
    }


}
