package ch.roche.inventory.mapper;

import ch.roche.inventory.dto.OrderRequest;
import ch.roche.inventory.dto.OrderResponse;
import ch.roche.inventory.entity.OrderEntity;
import ch.roche.inventory.entity.Product;
import ch.roche.inventory.service.ProductService;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@Mapper(componentModel = "spring",
        uses = {ProductMapper.class})
public abstract class OrderMapper {

    @Autowired
    private ProductService productService;

    @Mapping(target = "totalPrice", ignore = true)
    public abstract OrderResponse entityToResponse(OrderEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "products", ignore = true)
    @Mapping(target = "created", ignore = true)
    public abstract OrderEntity requestToEntity(OrderRequest request);

    @AfterMapping
    void afterRequestToEntityMapping(@MappingTarget OrderEntity target, OrderRequest source) {
        for (Long productId : source.getProducts()) {
            target.addProduct(productService.findProductById(productId));
        }
    }

    @AfterMapping
    void afterRequestToEntityMapping(@MappingTarget OrderResponse target, OrderEntity source) {
        BigDecimal total = BigDecimal.ZERO;
        for (Product product : source.getProducts()) {
            total = total.add(product.getPrice());
        }
        target.setTotalPrice(total);
    }
}
