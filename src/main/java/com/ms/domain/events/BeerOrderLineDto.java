package com.ms.domain.events;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerOrderLineDto{


    private String upc;
    private String beerName;
    private UUID beerId;
    private Integer orderQuantity = 0;
    private String beerStyle;
    private BigDecimal price;
}
