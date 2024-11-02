package a4.KU_TY_backend.KU_TY_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class LoginException extends RuntimeException{
    public LoginException(String message){
        super(message);
    }
}
