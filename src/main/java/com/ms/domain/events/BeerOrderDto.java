package com.ms.domain.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerOrderDto {

    private UUID id;
    private String customerRef;
    private List<BeerOrderLineDto> beerOrderLines;
    private String orderStatus;
    private String orderStatusCallbackUrl;
}
