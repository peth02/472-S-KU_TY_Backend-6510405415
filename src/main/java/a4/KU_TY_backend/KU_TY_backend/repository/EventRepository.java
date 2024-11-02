package a4.KU_TY_backend.KU_TY_backend.repository;

import a4.KU_TY_backend.KU_TY_backend.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
}
