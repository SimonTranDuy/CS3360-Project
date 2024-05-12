package backend.com.example.backendcs3360.exceptions;

public class PhoneNumberExistsException extends RuntimeException{
    public PhoneNumberExistsException(String message){
        super(message);
    }
}
