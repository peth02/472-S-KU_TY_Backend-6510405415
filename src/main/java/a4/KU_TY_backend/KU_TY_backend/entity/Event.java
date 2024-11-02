package a4.KU_TY_backend.KU_TY_backend.entity;

import a4.KU_TY_backend.KU_TY_backend.common.EventStatus;
import a4.KU_TY_backend.KU_TY_backend.response.EventResponse;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Entity
@Data
public class Event {
    @Id
    @GeneratedValue
    private UUID eventId;
    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;
    @Column(nullable = false)
    private String name;
    private String description;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    @Enumerated(EnumType.STRING)
    private EventStatus status = EventStatus.CLOSED;
    @Column(name = "start_date")
    private LocalDateTime startDate;
    private String location;
    @OneToMany(mappedBy = "event")
    @JsonIgnore
    private List<EventUser> joinedUserList;
    public EventResponse toResponse() {
        EventResponse dto = new EventResponse();
        dto.setEventId(eventId);
        dto.setCreatedBy(createdBy.getUserId());
        dto.setName(name);
        dto.setDescription(description);
        dto.setCreatedAt(createdAt);
        dto.setUpdatedAt(updatedAt);
        dto.setStatus(status);
        dto.setStartDate(startDate);
        dto.setLocation(location);
        return dto;
    }
}
