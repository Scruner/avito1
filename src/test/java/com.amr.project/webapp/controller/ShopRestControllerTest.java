package com.amr.project.webapp.controller;

import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.ShopService;
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

import static org.assertj.core.api.Assertions.assertThat;
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
public class ShopRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ShopService shopService;

    private List<Shop> shopList;

    @Before
    public void initShops() {
        shopList = new ArrayList<>();

        shopList.add(new Shop(1L, null, "clothes@mail.com", "89177777777",
                "clothes", 11L, null, null, 111, 111, null,
                null, false, true, "checking", false));

        shopList.add(new Shop(2L, null, "cars@mail.com", "89111111111",
                "cars", 22L, null, null, 222, 222, null,
                null, false, true, "checking", false));
    }


    @Test
    public void test() throws Exception {
        assertThat(mockMvc).isNotNull();
    }

    @Test
    public void getAllShopTest() throws Exception {
        when(shopService.findAll()).thenReturn(shopList);

        mockMvc.perform(get("/shop").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email").value("clothes@mail.com"))
                .andExpect(jsonPath("$[1].email").value("cars@mail.com"));
    }

    @Test
    public void getShopByIdTest() throws Exception {
        given(shopService.findById(shopList.get(0).getId())).willReturn(shopList.get(0));

        mockMvc.perform(get("/shop/{id}", shopList.get(0).getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.phone", is(shopList.get(0).getPhone())));

    }

    @Test
    public void createShopTest() throws Exception {
        when(shopService.persist(shopList.get(0))).thenReturn(shopList.get(0));

        mockMvc.perform(post("/shop")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(shopList.get(0))))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].phone", is(shopList.get(0).getPhone())));

        verify(shopService).persist(any(Shop.class));

    }

    @Test
    public void deleteShopTest() throws Exception {
        mockMvc.perform(delete("/shop/1/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(shopList)))
                .andDo(print())
                .andExpect(status().isOk());
    }


}
