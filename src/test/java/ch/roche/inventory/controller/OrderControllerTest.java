package ch.roche.inventory.controller;

import ch.roche.inventory.config.MigrationConfiguration;
import ch.roche.inventory.dto.OrderRequest;
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

import java.time.Instant;
import java.util.List;

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
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    void shouldReturnOrders() throws Exception {
        mockMvc.perform(get("/api/v1/orders")
                .param("from", Instant.now().minusSeconds(60).toString())
                .param("to", Instant.now().plusSeconds(60).toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].email", equalTo("alfred.werner@roche.ch")))
                .andExpect(jsonPath("$[0].totalPrice", equalTo(271.10)))
                .andExpect(jsonPath("$[1].email", equalTo("paul.karrer@roche.ch")))
                .andExpect(jsonPath("$[1].totalPrice", equalTo(1160.29)))
                .andExpect(jsonPath("$[2].email", equalTo("jaroslav.heyrovsky@roche.ch")))
                .andExpect(jsonPath("$[2].totalPrice", equalTo(101.55)))
                .andExpect(jsonPath("$[3].email", equalTo("kurt.wuethrich@roche.ch")))
                .andExpect(jsonPath("$[3].totalPrice", equalTo(580.01)))
                .andExpect(jsonPath("$[4].email", equalTo("jacques.dubochet@roche.ch")))
                .andExpect(jsonPath("$[4].totalPrice", equalTo(20.54)));
    }

    @Test
    @Order(2)
    void shouldReturnEmptyOrders() throws Exception {
        mockMvc.perform(get("/api/v1/orders")
                .param("from", Instant.now().toString())
                .param("to", Instant.now().plusSeconds(60).toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    @Order(3)
    void shouldCreateNewOrder() throws Exception {

        OrderRequest request = new OrderRequest();
        request.setEmail("lapacek.ondrej@gmail.com");
        request.setProducts(List.of(9L));
        String payload = new ObjectMapper().writeValueAsString(request);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("email", equalTo("lapacek.ondrej@gmail.com")))
                .andExpect(jsonPath("totalPrice", equalTo(9999.0)))
                .andExpect(jsonPath("products", hasSize(1)))
                .andExpect(jsonPath("products[0].name", equalTo("Toilette paper")));
    }

    @Test
    @Order(4)
    void toilettePaperSoldOut() throws Exception {
        mockMvc.perform(delete("/api/v1/products/9"))
                .andExpect(status().isNoContent());
    }
}
