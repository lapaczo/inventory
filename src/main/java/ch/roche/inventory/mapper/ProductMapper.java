package ch.roche.inventory.mapper;

import ch.roche.inventory.dto.ProductRequest;
import ch.roche.inventory.dto.ProductResponse;
import ch.roche.inventory.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {

    public abstract ProductResponse entityToResponse(Product entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    public abstract Product requestToEntity(ProductRequest request);

    public void copyRequestToEntity(Product entity, ProductRequest request) {
        entity.setName(request.getName());
        entity.setPrice(request.getPrice());
    }
}
