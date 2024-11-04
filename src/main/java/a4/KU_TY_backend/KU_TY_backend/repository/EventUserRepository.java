package a4.KU_TY_backend.KU_TY_backend.repository;

import a4.KU_TY_backend.KU_TY_backend.entity.EventUser;
import a4.KU_TY_backend.KU_TY_backend.entity.EventUserKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventUserRepository extends JpaRepository<EventUser, EventUserKey> {

}
