package a4.KU_TY_backend.KU_TY_backend.request;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
public class CreateEventRequest {
    private UUID createdBy;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalTime startTime;
    private String location;
    private int capacity;
    private String typeName;
}
