package com.ms.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.web.model.BeerDTO;
import com.ms.web.model.BeerStyleEnum;
import com.ms.web.services.BeerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureWebClient
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BeerService beerService;

    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";
    @Test
    public void getBeerById() throws Exception {

       given(beerService.getBeerById(UUID.randomUUID(), false)).willReturn(getValidBeerWithId());

        mockMvc.perform(get("/api/v1/beer/{beerId}",UUID.randomUUID().toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void saveNewBeer()throws Exception{
        BeerDTO beerDTO = getValidBeer();
        String json = objectMapper.writeValueAsString(beerDTO);

        given(beerService.saveMyBeer(beerDTO)).willReturn(getValidBeerWithId());
        mockMvc.perform(post("/api/v1/beer").contentType(MediaType.APPLICATION_JSON)
                .content(json)).andExpect(status().isCreated());

    }

    @Test
    public void updateBeer()throws Exception{
        BeerDTO beerDTO = getValidBeer();

        String json = objectMapper.writeValueAsString(beerDTO);
        given(beerService.updateMyBeer(UUID.randomUUID(),beerDTO)).willReturn(getValidBeerWithId());
        mockMvc.perform(put("/api/v1/beer/"+UUID.randomUUID()).contentType(MediaType.APPLICATION_JSON)
                        .content(json)).andExpect(status().isNoContent());
    }

    private BeerDTO getValidBeer() {

        return BeerDTO.builder()
                .beerName("MillerLite")
                .beerStyle(BeerStyleEnum.PALE_ALE)
                .price(new BigDecimal("2.99"))
                .upc(BEER_1_UPC).build();
    }

    private BeerDTO getValidBeerWithId(){
        return BeerDTO.builder()
                .beerName("MillerLite")
                .beerStyle(BeerStyleEnum.PALE_ALE)
                .price(new BigDecimal("2.99"))
                .upc(BEER_1_UPC)
                .beerId(UUID.randomUUID())
                .version(1)
                .quantityOnHand(100).createdDate(OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.UTC))
                .lastModifiedDate(OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.UTC))
                .build();
    }
}