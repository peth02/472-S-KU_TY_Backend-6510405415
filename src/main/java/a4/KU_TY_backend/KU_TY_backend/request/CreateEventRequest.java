package a4.KU_TY_backend.KU_TY_backend.request;

import a4.KU_TY_backend.KU_TY_backend.entity.Event;
import lombok.Data;

@Data
public class CreateEventRequest {
    private Event event;
}
