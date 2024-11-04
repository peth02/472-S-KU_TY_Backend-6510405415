package a4.KU_TY_backend.KU_TY_backend.repository;

import a4.KU_TY_backend.KU_TY_backend.entity.EventType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface EventTypeRepository extends JpaRepository<EventType, UUID> {
    EventType findByTypeName(String typeName);
}
