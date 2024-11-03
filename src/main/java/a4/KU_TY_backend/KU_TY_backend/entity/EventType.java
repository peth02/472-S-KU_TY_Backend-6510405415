package a4.KU_TY_backend.KU_TY_backend.entity;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
public class EventType{
    @Id
    @GeneratedValue
    private UUID typeId;
    @Column(nullable = false, name = "type_name", unique = true)
    private String typeName;
    @OneToMany(mappedBy = "eventType")
    @JsonIgnore
    private List<Event> eventList;
}