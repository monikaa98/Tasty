package project.tastyfood.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND, reason = "Picture not provided!")
public class PictureNotProvidedException extends RuntimeException{

}
