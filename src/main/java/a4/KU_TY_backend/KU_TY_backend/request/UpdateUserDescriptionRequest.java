package a4.KU_TY_backend.KU_TY_backend.request;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.UUID;
@Data
public class UpdateUserDescriptionRequest {
    private UUID userId;
    private String description;
}
