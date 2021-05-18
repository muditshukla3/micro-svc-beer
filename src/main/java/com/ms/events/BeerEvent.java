package com.ms.events;

import com.ms.domain.BeerDTO;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerEvent implements Serializable {

    static final long serialVersionUID = -8638840041554945019L;

    private BeerDTO beerDTO;
}
