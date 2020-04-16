package ch.roche.inventory.dto;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@ToString
public class ProductResponse {

    private Long id;
    private String name;
    private BigDecimal price;
    private Instant created;
}
