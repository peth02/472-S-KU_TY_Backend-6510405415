package a4.KU_TY_backend.KU_TY_backend.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.UUID;

public class Event {
    @Id
    @GeneratedValue
    private UUID eventId;
    private String name;
    private String description;
    private String createBy;
}
