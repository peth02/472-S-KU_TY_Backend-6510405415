package a4.KU_TY_backend.KU_TY_backend.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "feedback")
public class Feedback {
    @EmbeddedId
    private FeedbackKey key;
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @MapsId("eventId")
    @JoinColumn(name = "event_id")
    private Event event;
    @Column(nullable = false)
    private String feedback;

}
