package com.ms.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDTO {

    @Null
    private UUID beerId;

    @Null
    private Integer version;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ" ,shape = JsonFormat.Shape.STRING)
    @Null
    private OffsetDateTime createdDate;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ" ,shape = JsonFormat.Shape.STRING)
    @Null
    private OffsetDateTime lastModifiedDate;

    @NotBlank
    @Size(min = 3)
    private String beerName;

    @NotNull
    private BeerStyleEnum beerStyle;

    @NotNull
    private String upc;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Positive
    @NotNull
    private BigDecimal price;

    @Positive
    private Integer quantityOnHand;

}
