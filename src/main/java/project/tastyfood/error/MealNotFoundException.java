package project.tastyfood.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND, reason = "Meal not found")
public class MealNotFoundException extends RuntimeException{
    public MealNotFoundException(String message) {
        super(message);
    }
}
