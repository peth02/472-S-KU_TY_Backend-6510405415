package a4.KU_TY_backend.KU_TY_backend.request;

import lombok.Data;

import java.util.UUID;

@Data
public class JoinEventRequest {
    private UUID userId;
    private UUID eventId;
}
