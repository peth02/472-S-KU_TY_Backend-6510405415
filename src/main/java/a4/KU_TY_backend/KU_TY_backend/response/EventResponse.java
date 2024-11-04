package a4.KU_TY_backend.KU_TY_backend.response;

import a4.KU_TY_backend.KU_TY_backend.common.EventStatus;
import a4.KU_TY_backend.KU_TY_backend.entity.User;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
@Data
public class EventResponse {
    private UUID eventId;
    private User createdBy;
    private String name;
    private String description;
    private EventStatus status;
    private LocalDate startDate;
    private LocalTime startTime;
    private String location;
    private int capacity;
    private int attendeeCount;
    private String imageUrl;
    private String TypeName;
}
