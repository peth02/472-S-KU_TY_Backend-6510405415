package a4.KU_TY_backend.KU_TY_backend.entity;

import a4.KU_TY_backend.KU_TY_backend.common.EventStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Data
public class Event {
    @Id
    @GeneratedValue
    private UUID eventId;
    @ManyToOne
    @Column(name = "created_by", nullable = false)
    private User createdBy;
    @Column(nullable = false)
    private String name;
    private String description;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    private EventStatus status = EventStatus.CLOSED;
    @Column(name = "start_date")
    private LocalDateTime startDate;
    private String location;
}
