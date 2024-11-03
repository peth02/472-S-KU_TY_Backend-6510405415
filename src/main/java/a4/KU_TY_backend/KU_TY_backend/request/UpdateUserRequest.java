package a4.KU_TY_backend.KU_TY_backend.request;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateUserRequest {
    private UUID userId;
    private String description;
    private String email;
    private String firstName;
    private String lastName;
}
