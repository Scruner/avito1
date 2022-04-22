package com.amr.project.webapp.controller;

import com.amr.project.model.entity.Discount;
import com.amr.project.service.abstracts.DiscountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DiscountRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    DiscountService discountService;

    private List<Discount> discounts;

    @Before
    public void initObjects() {
        discounts = new ArrayList<>();
        discounts.add(new Discount(1L, 5, 10, null, null));
        discounts.add(new Discount(2L, 10, 15, null, null));
        discounts.add(new Discount(3L, 15, 25, null, null));
    }

    @Test
    public void getAllDiscountsTest() throws Exception {
        when(discountService.findAll()).thenReturn(discounts);

        mockMvc.perform(get("/discounts").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(3)))
                .andExpect(jsonPath("$[0].minOrder").value(5))
                .andExpect(jsonPath("$[0].percentage").value(10))
                .andExpect(jsonPath("$[1].minOrder").value(10))
                .andExpect(jsonPath("$[1].percentage").value(15))
                .andExpect(jsonPath("$[2].minOrder").value(15))
                .andExpect(jsonPath("$[2].percentage").value(25));
    }

    @Test
    public void getDiscountDtoByIdTest() throws Exception {
        given(discountService.findById(discounts.get(1).getId())).willReturn(discounts.get(1));

        mockMvc.perform(get("/discounts/{id}", discounts.get(1).getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.minOrder", is(discounts.get(1).getMinOrder())))
                .andExpect(jsonPath("$.percentage", is(discounts.get(1).getPercentage())));
    }

    @Test
    public void createDiscountTest() throws Exception {
        when(discountService.persist(discounts.get(1))).thenReturn(discounts.get(1));

       mockMvc.perform(post("/discounts").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(discounts.get(1))))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.minOrder", is(discounts.get(1).getMinOrder())))
                .andExpect(jsonPath("$.percentage", is(discounts.get(1).getPercentage())));

        verify(discountService).persist(any(Discount.class));
    }

    @Test
    public void deleteDiscountTest() throws Exception {
        mockMvc.perform(delete("/discounts/1/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(discounts.get(0))))
                .andExpect(status().isOk());
    }
}
