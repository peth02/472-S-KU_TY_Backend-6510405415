package a4.KU_TY_backend.KU_TY_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
@Entity
@Data
public class User {
    @Id
    @GeneratedValue
    private String userId;
    private String username;
    private String description;
}
