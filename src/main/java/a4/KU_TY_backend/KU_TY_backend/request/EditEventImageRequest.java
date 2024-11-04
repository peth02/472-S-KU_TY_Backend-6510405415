package a4.KU_TY_backend.KU_TY_backend.request;

import lombok.Data;

import java.util.UUID;

@Data
public class EditEventImageRequest {
    private UUID eventId;
    private String imageUrl;
}
