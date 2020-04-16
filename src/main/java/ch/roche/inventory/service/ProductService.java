package ch.roche.inventory.service;

import ch.roche.inventory.dto.ProductRequest;
import ch.roche.inventory.dto.ProductResponse;
import ch.roche.inventory.entity.Product;
import ch.roche.inventory.mapper.ProductMapper;
import ch.roche.inventory.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    @Transactional
    public List<ProductResponse> listProducts() {
        log.info("List all products.");
        return repository.findAll().stream()
                .map(mapper::entityToResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProductResponse getProduct(Long id) {
        log.info("Get product by id: {}.", id);
        return mapper.entityToResponse(findProductById(id));
    }

    @Transactional
    public ProductResponse createProduct(ProductRequest request) {
        log.info("Create new product: {}.", request);
        Product product = repository.save(mapper.requestToEntity(request));
        log.info("Product with id: {} was created.", product.getId());
        return mapper.entityToResponse(product);
    }

    @Transactional
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        log.info("Update product with id: {}, {}.", id, request);
        Product product = findProductById(id);
        mapper.copyRequestToEntity(product, request);
        Product result = repository.save(product);
        log.info("Product with id: {} was updated.", result.getId());
        return mapper.entityToResponse(result);
    }

    @Transactional
    public void deleteProduct(Long id) {
        log.info("Delete product with id: {}.", id);
        repository.deleteById(id);
        log.info("Product with id: {} were removed.", id);
    }

    public Product findProductById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found by id: " + id));
    }
}
