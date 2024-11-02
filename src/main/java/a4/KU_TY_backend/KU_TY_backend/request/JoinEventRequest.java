package a4.KU_TY_backend.KU_TY_backend.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JoinEventRequest {
    private UUID userId;
    private UUID eventId;
}
