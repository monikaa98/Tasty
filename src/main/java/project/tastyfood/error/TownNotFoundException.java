package project.tastyfood.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND, reason = "Town not found")
public class TownNotFoundException extends RuntimeException{
    public TownNotFoundException(String message) {
        super(message);
    }
}
