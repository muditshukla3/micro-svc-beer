package com.ms.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.web.model.BeerDTO;
import com.ms.web.model.BeerStyleEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs
@WebMvcTest
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void getBeerById() throws Exception {

        mockMvc.perform(get("/api/v1/beer/{beerId}",UUID.randomUUID().toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("v1/beer-get",
                        pathParameters(
                          parameterWithName("beerId").description("UUID for desired beer to get")
                        )
                ,responseFields(
                                fieldWithPath("id").description("Id of Beer"),
                                fieldWithPath("version").description("Version Number"),
                                fieldWithPath("createdDate").description("Created Date of Beer"),
                                fieldWithPath("lastModifiedDate").description("Last Modified Date of Beer"),
                                fieldWithPath("beerName").description("Beer Name"),
                                fieldWithPath("beerStyle").description("Style Of Beer"),
                                fieldWithPath("upc").description(",Unique Product Code"),
                                fieldWithPath("price").description("Price Of Beer"),
                                fieldWithPath("quantityOnHand").description("Quantity In Hand")
                        )
                ));
    }

    private static class ConstrainedFields {

        private final ConstraintDescriptions constraintDescriptions;

        ConstrainedFields(Class<?> input) {
            this.constraintDescriptions = new ConstraintDescriptions(input);
        }

        private FieldDescriptor withPath(String path) {
            return fieldWithPath(path).attributes(key("constraints").value(StringUtils
                    .collectionToDelimitedString(this.constraintDescriptions
                            .descriptionsForProperty(path), ". ")));
        }
    }
    @Test
    public void saveNewBeer()throws Exception{
        BeerDTO beerDTO = getValidBeer();
        String json = objectMapper.writeValueAsString(beerDTO);
        ConstrainedFields fields = new ConstrainedFields(BeerDTO.class);

        mockMvc.perform(post("/api/v1/beer").contentType(MediaType.APPLICATION_JSON)
                .content(json)).andExpect(status().isCreated())
                .andDo(document("v1/beer-post",requestFields(
                        fields.withPath("id").ignored(),
                        fields.withPath("version").ignored(),
                        fields.withPath("createdDate").ignored(),
                        fields.withPath("lastModifiedDate").ignored(),
                        fields.withPath("beerName").description("Beer Name"),
                        fields.withPath("beerStyle").description("Style Of Beer"),
                        fields.withPath("upc").description(",Unique Product Code"),
                        fields.withPath("price").description("Price Of Beer"),
                        fields.withPath("quantityOnHand").description("Quantity In Hand")
                        )
                ));

    }

    @Test
    public void updateBeer()throws Exception{
        BeerDTO beerDTO = getValidBeer();

        String json = objectMapper.writeValueAsString(beerDTO);
        mockMvc.perform(put("/api/v1/beer/"+UUID.randomUUID()).contentType(MediaType.APPLICATION_JSON)
                        .content(json)).andExpect(status().isNoContent());
    }

    private BeerDTO getValidBeer() {

        return BeerDTO.builder()
                .beerName("MillerLite").beerStyle(BeerStyleEnum.PALE_ALE).price(new BigDecimal("2.99"))
                .upc(10920910L).build();
    }
}