package ch.roche.inventory.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public abstract class SoftDeletedEntity extends AuditedEntity {

    @ColumnDefault("false")
    private boolean deleted = false;
}
