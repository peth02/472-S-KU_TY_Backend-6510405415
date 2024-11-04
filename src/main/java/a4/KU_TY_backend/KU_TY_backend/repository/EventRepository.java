package a4.KU_TY_backend.KU_TY_backend.repository;

import a4.KU_TY_backend.KU_TY_backend.common.EventStatus;
import a4.KU_TY_backend.KU_TY_backend.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
    List<Event> getByStatus(EventStatus status);
}
