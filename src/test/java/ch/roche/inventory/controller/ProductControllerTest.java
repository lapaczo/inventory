package ch.roche.inventory.controller;

import ch.roche.inventory.config.MigrationConfiguration;
import ch.roche.inventory.dto.ProductRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(MigrationConfiguration.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    void shouldReturnProducts() throws Exception {
        mockMvc.perform(get("/api/v1/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(9)))
                .andExpect(jsonPath("$[0].name", equalTo("Potato")))
                .andExpect(jsonPath("$[1].name", equalTo("Tomato")))
                .andExpect(jsonPath("$[2].name", equalTo("Milk")))
                .andExpect(jsonPath("$[3].name", equalTo("Butter")))
                .andExpect(jsonPath("$[4].name", equalTo("Oranges")))
                .andExpect(jsonPath("$[5].name", equalTo("Cheese")))
                .andExpect(jsonPath("$[6].name", equalTo("Ham")))
                .andExpect(jsonPath("$[7].name", equalTo("Bread")))
                .andExpect(jsonPath("$[8].name", equalTo("Toilette paper")));
    }

    @Test
    @Order(2)
    void shouldRemoveProduct() throws Exception {
        mockMvc.perform(delete("/api/v1/products/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @Order(3)
    void shouldReturnProductsWithoutFirst() throws Exception {
        mockMvc.perform(get("/api/v1/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(8)))
                .andExpect(jsonPath("$[0].name", equalTo("Tomato")))
                .andExpect(jsonPath("$[1].name", equalTo("Milk")))
                .andExpect(jsonPath("$[2].name", equalTo("Butter")))
                .andExpect(jsonPath("$[3].name", equalTo("Oranges")))
                .andExpect(jsonPath("$[4].name", equalTo("Cheese")))
                .andExpect(jsonPath("$[5].name", equalTo("Ham")))
                .andExpect(jsonPath("$[6].name", equalTo("Bread")))
                .andExpect(jsonPath("$[7].name", equalTo("Toilette paper")));
    }

    @Test
    @Order(4)
    void shouldResultInNotFound() throws Exception {
        mockMvc.perform(get("/api/v1/products/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(5)
    void shouldCreateNewProduct() throws Exception {

        ProductRequest request = new ProductRequest();
        request.setName("Soda");
        request.setPrice(BigDecimal.valueOf(11.1));
        String payload = new ObjectMapper().writeValueAsString(request);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("name", equalTo("Soda")))
                .andExpect(jsonPath("price", equalTo(11.1)));
    }

    @Test
    @Order(6)
    void shouldUpdateNewlyCreatedProduct() throws Exception {
        ProductRequest request = new ProductRequest();
        request.setName("Baking Soda");
        request.setPrice(BigDecimal.valueOf(5.1));
        String payload = new ObjectMapper().writeValueAsString(request);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/products/10")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", equalTo("Baking Soda")))
                .andExpect(jsonPath("price", equalTo(5.1)));
    }
}
