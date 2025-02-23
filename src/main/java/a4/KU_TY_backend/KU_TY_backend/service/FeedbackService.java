package a4.KU_TY_backend.KU_TY_backend.service;

import a4.KU_TY_backend.KU_TY_backend.entity.Event;
import a4.KU_TY_backend.KU_TY_backend.entity.Feedback;
import a4.KU_TY_backend.KU_TY_backend.entity.User;
import a4.KU_TY_backend.KU_TY_backend.repository.EventRepository;
import a4.KU_TY_backend.KU_TY_backend.repository.FeedbackRepository;
import a4.KU_TY_backend.KU_TY_backend.repository.UserRepository;
import a4.KU_TY_backend.KU_TY_backend.request.GiveFeedbackRequest;
import a4.KU_TY_backend.KU_TY_backend.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FeedbackService {
    @Autowired
    private Validator validator;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private FeedbackRepository feedbackRepository;
    public void giveFeedback(GiveFeedbackRequest request){
        UUID userId = request.getUserId();
        UUID eventId = request.getEventId();
        String feedback = request.getFeedback();
        validator.userIdValidate(userId);
        validator.eventIdValidate(eventId);
        User user = userRepository.findById(userId).get();
        Event event = eventRepository.findById(eventId).get();

        Feedback feedbackObject = new Feedback();
        feedbackObject.setUser(user);
        feedbackObject.setEvent(event);
        feedbackObject.setFeedback(feedback);

        feedbackRepository.save(feedbackObject);

    }
}
