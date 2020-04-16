package ch.roche.inventory.dto;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@ToString
public class OrderResponse {

    private Long id;
    private String email;
    private Instant created;
    private List<ProductResponse> products;
    private BigDecimal totalPrice;
}
