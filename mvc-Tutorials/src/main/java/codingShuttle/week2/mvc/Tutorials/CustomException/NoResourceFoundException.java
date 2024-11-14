package codingShuttle.week2.mvc.Tutorials.CustomException;

public class NoResourceFoundException extends RuntimeException{
    public NoResourceFoundException(String message) {
        super(message);
    }
}

