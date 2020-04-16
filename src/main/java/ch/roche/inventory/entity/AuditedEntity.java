package ch.roche.inventory.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class AuditedEntity {

    @Column(nullable = false, insertable = false, updatable = false)
    @CreatedDate
    @ColumnDefault("now()")
    private Instant created = Instant.now();
}
