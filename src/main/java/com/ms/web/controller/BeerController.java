package com.ms.web.controller;

import com.ms.web.model.BeerDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDTO> getBeerById(@PathVariable UUID beerId){

        //todo impl
        return new ResponseEntity<>(BeerDTO.builder().build(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveBeer(@Validated @RequestBody BeerDTO beerDTO){
        //todo impl
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeer(@PathVariable UUID beerId,@Valid @RequestBody BeerDTO beerDTO){
        //todo impl
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}
