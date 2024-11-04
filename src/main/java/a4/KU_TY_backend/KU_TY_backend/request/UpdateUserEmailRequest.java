package a4.KU_TY_backend.KU_TY_backend.request;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateUserEmailRequest {
    UUID userId;
    String email;
}
