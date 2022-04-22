package com.amr.project.webapp.controller;

import com.amr.project.model.entity.Item;
import com.amr.project.service.abstracts.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class ItemRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    ItemService itemService;

//    private List<Item> itemList;
//
//    @BeforeEach
//    void setUp() {
//        itemList = new ArrayList<>();
//        itemList.add(new Item(1L, "name_1", new BigDecimal(1000), new BigDecimal(500), 1L,
//                null, null, 10, 0.5, "description_1", 1,
//                null, false,"moderatedRejectReason_1", false));
//        itemList.add( new Item(2L, "name_2", new BigDecimal(1500), new BigDecimal(1000), 1L,
//                null, null, 10, 0.5, "description_2", 1,
//                null, false,"moderatedRejectReason_2", false));
//        itemList.add(new Item(3L, "name_3", new BigDecimal(2000), new BigDecimal(1500), 1L,
//                null, null, 10, 0.5, "description_3", 1,
//                null, false,"moderatedRejectReason_3", false));
//    }


    @Test
    void getItemDtoByIdTest() throws Exception {
        final Item item = new Item(1L, "name", new BigDecimal(100), new BigDecimal(50), 1L,
                null, null, 10, 0.5, "description", 1,
                null, false,"moderatedRejectReason", false);

        given(itemService.findById(item.getId())).willReturn(item);

        mockMvc.perform(get("/item/{id}", item.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(item.getName())))
                .andExpect(jsonPath("$.description", is(item.getDescription())));

    }

    @Test
    void createItemTest() throws Exception {
        final Item item = new Item(1L, "name", new BigDecimal(100), new BigDecimal(50), 1L,
                null, null, 10, 0.5, "description", 1,
                null, false,"moderatedRejectReason", false);

        given(itemService.persist(any(Item.class))).willAnswer((i) -> i.getArgument(0));

        mockMvc.perform(post("/item").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(item)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(item.getName())))
                .andExpect(jsonPath("$.description", is(item.getDescription())));
    }
}
