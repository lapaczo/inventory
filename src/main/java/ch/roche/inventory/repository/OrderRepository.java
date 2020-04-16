package ch.roche.inventory.repository;

import ch.roche.inventory.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query("SELECT o FROM OrderEntity o "
            + "WHERE o.created BETWEEN :from AND :to "
            + "ORDER BY o.created")
    List<OrderEntity> findAllByCreated(Instant from, Instant to);
}
