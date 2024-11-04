package a4.KU_TY_backend.KU_TY_backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class EventUserKey implements Serializable {
    @Column(name = "event_id")
    private UUID eventId;
    @Column(name = "user_id")
    private UUID userId;
}
