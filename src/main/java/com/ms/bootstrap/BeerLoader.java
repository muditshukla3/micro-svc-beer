package com.ms.bootstrap;

import com.ms.domain.Beer;
import com.ms.repositories.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BeerLoader implements CommandLineRunner {

    private final BeerRepository beerRepository;

    public BeerLoader(BeerRepository beerRepository){
        this.beerRepository=beerRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        loadBeerObjects();
    }

    private void loadBeerObjects() {
        if(beerRepository.count()==0){
            beerRepository.save(Beer.builder()
                    .beerName("Mango Bobs")
                    .beerStyle("IPA")
                    .quantityToBrew(200)
                    .upc(33700000L)
                    .price(new BigDecimal(12.65))
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("Galaxy Cat")
                    .beerStyle("PALE_ALE")
                    .quantityToBrew(100)
                    .upc(33700001L)
                    .price(new BigDecimal(10.65))
                    .build());


            beerRepository.save(Beer.builder()
                    .beerName("Budweiser")
                    .beerStyle("Gose")
                    .quantityToBrew(300)
                    .upc(33700002L)
                    .price(new BigDecimal(6.65))
                    .build());
        }

        System.out.println("Total Beers::: "+beerRepository.count());
    }
}
