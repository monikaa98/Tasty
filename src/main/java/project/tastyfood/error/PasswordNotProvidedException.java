package project.tastyfood.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND, reason = "Password not provided!")
public class PasswordNotProvidedException  extends RuntimeException{

}
