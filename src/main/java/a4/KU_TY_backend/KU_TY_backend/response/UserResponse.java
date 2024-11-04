package a4.KU_TY_backend.KU_TY_backend.response;
import lombok.Data;
import java.util.UUID;

@Data
public class UserResponse {
    private UUID userId;
    private String username;
    private String description;
    private String email;
    private String firstName;
    private String lastName;
    private String departmentNameTh;
    private String majorName;
    private String imageUrl;
}
