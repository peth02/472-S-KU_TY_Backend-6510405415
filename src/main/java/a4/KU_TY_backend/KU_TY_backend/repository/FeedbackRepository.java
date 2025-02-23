package a4.KU_TY_backend.KU_TY_backend.repository;

import a4.KU_TY_backend.KU_TY_backend.entity.EventUser;
import a4.KU_TY_backend.KU_TY_backend.entity.EventUserKey;
import a4.KU_TY_backend.KU_TY_backend.entity.Feedback;
import a4.KU_TY_backend.KU_TY_backend.entity.FeedbackKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository  extends JpaRepository<Feedback, FeedbackKey> {
}
