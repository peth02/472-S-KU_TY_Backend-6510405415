package a4.KU_TY_backend.KU_TY_backend.service;

import a4.KU_TY_backend.KU_TY_backend.entity.Event;
import a4.KU_TY_backend.KU_TY_backend.entity.EventType;
import a4.KU_TY_backend.KU_TY_backend.exception.ConflictException;
import a4.KU_TY_backend.KU_TY_backend.exception.SystemException;
import a4.KU_TY_backend.KU_TY_backend.repository.EventTypeRepository;
import a4.KU_TY_backend.KU_TY_backend.request.CreateEventTypeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventTypeService {
    @Autowired
    EventTypeRepository repository;
    public EventType createEventType(CreateEventTypeRequest request){
        String typeName = request.getTypeName();
        if(typeName == null) throw new SystemException("Type name must not be null");
        if(repository.findByTypeName(typeName) != null) throw new ConflictException("This type name already existed");
        EventType eventType = new EventType();
        eventType.setTypeName(typeName);
        return repository.save(eventType);
    }
}
