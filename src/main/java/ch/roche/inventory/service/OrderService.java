package ch.roche.inventory.service;

import ch.roche.inventory.dto.OrderRequest;
import ch.roche.inventory.dto.OrderResponse;
import ch.roche.inventory.entity.OrderEntity;
import ch.roche.inventory.mapper.OrderMapper;
import ch.roche.inventory.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final OrderMapper mapper;

    @Transactional
    public List<OrderResponse> listOrders(Instant from, Instant to) {
        log.info("List all orders between {} and {}.", from, to);
        return repository.findAllByCreated(from, to).stream()
                .map(mapper::entityToResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public OrderResponse createOrder(OrderRequest request) {
        log.info("Create new order: {}.", request);
        OrderEntity order = repository.save(mapper.requestToEntity(request));
        return mapper.entityToResponse(order);
    }
}
