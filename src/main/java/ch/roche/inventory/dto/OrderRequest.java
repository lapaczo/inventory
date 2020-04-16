package ch.roche.inventory.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ToString
public class OrderRequest {

    @NotBlank
    private String email;
    @NotNull
    private List<Long> products;
}
