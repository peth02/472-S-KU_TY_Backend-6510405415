package a4.KU_TY_backend.KU_TY_backend.response;

import a4.KU_TY_backend.KU_TY_backend.common.EventStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
public class EventResponse {
    private UUID eventId;
    private UUID createdBy;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private EventStatus status;
    private LocalDateTime startDate;
    private String location;
}
