package backend.com.example.backendcs3360.exceptions;

public class InvalidDataException extends RuntimeException{
    public InvalidDataException(String message){
        super(message);
    }
}
