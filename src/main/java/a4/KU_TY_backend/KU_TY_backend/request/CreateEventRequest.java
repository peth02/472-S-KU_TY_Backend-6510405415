package a4.KU_TY_backend.KU_TY_backend.request;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CreateEventRequest {
    private UUID createdBy;
    private String name;
    private String description;
    private LocalDateTime startDate;
    private String location;
    private int capacity;
    private String imageUrl;
}
