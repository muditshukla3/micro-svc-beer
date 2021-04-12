package com.ms.domain;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.ms.web.model.BeerStyleEnum;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Beer extends BaseEntity {

    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false )
    private UUID beerId;

    private String beerName;
    private BeerStyleEnum beerStyle;

    @Column(unique = true)
    private String upc;
    private BigDecimal price;

    private Integer minOnHand;
    private Integer quantityToBrew;
}
