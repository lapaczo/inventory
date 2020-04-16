package ch.roche.inventory.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@ToString
public class ProductRequest {

    @NotBlank
    private String name;
    @Min(value = 0L, message = "Price must be positive number.")
    private BigDecimal price;
}
