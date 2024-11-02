package a4.KU_TY_backend.KU_TY_backend.exception;

import a4.KU_TY_backend.KU_TY_backend.request.JoinEventRequest;
import a4.KU_TY_backend.KU_TY_backend.response.ResponseHandler;
import org.hibernate.mapping.Join;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<Object> handleLoginException(LoginException ex) {
        return ResponseHandler.responseBuilder(ex.getMessage(), HttpStatus.UNAUTHORIZED, null); // ส่งข้อความ error ไปยัง response
    }
    @ExceptionHandler(SystemException.class)
    public ResponseEntity<Object> handleSystemException(SystemException ex) {
        return ResponseHandler.responseBuilder(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null); // ส่งข้อความ error ไปยัง response
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNoteFoundException(NotFoundException ex){
        return ResponseHandler.responseBuilder(ex.getMessage(), HttpStatus.NOT_FOUND, null); // ส่งข้อความ error ไปยัง response
    }
    @ExceptionHandler(JoinedEventException.class)
    public ResponseEntity<Object> handleJoinedEventException(JoinedEventException ex){
        return ResponseHandler.responseBuilder(ex.getMessage(), HttpStatus.CONFLICT, null); // ส่งข้อความ error ไปยัง response
    }
}