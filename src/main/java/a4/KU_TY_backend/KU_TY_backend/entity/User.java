package a4.KU_TY_backend.KU_TY_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue
    private UUID userId;
    @Column(nullable = false)
    private String username;
    private String description;
    private String email;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "department_name_th", nullable = false)
    private String departmentNameTh;
    @Column(name = "major_name", nullable = false)
    private String majorName;
    @OneToMany(mappedBy = "createdBy")
    private List<Event> eventList;
}
