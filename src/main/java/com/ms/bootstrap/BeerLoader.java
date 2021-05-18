package com.ms.bootstrap;

import com.ms.domain.Beer;
import com.ms.repositories.BeerRepository;
import com.ms.domain.BeerStyleEnum;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class BeerLoader implements CommandLineRunner {

    private final BeerRepository beerRepository;
    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";
    public static final UUID BEER_1_UUID = UUID.fromString("0a818933-087d-47f2-ad83-2f986ed087eb");
    public static final UUID BEER_2_UUID = UUID.fromString("a712d914-61ea-4623-8bd0-32c0f6545bfd");
    public static final UUID BEER_3_UUID = UUID.fromString("026cc3c8-3a0c-4083-a05b-e908048c1b08");

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
                    .beerId(BEER_1_UUID)
                    .minOnHand(12)
                    .upc(BEER_1_UPC)
                    .beerStyle(BeerStyleEnum.PALE_ALE)
                    .quantityToBrew(200)
                    .price(new BigDecimal(12.65))
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("Galaxy Cat")
                    .beerId(BEER_2_UUID)
                    .minOnHand(12)
                    .beerStyle(BeerStyleEnum.GOSE)
                    .quantityToBrew(100)
                    .upc(BEER_2_UPC)
                    .price(new BigDecimal(10.65))
                    .build());


            beerRepository.save(Beer.builder()
                    .beerName("Budweiser")
                    .beerId(BEER_3_UUID)
                    .minOnHand(12)
                    .beerStyle(BeerStyleEnum.LAGER)
                    .quantityToBrew(300)
                    .upc(BEER_3_UPC)
                    .price(new BigDecimal(6.65))
                    .build());
        }

        System.out.println("Total Beers::: "+beerRepository.count());
    }
}
