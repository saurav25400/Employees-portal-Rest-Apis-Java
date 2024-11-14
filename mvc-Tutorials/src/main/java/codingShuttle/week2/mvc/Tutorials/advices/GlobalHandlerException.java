package codingShuttle.week2.mvc.Tutorials.advices;

import codingShuttle.week2.mvc.Tutorials.CustomException.NoResourceFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.text.Collator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiError>NoResourceFound(NoResourceFoundException exception){
        ApiError apiError= ApiError.builder().status(HttpStatus.NOT_FOUND).message(exception.getMessage()).build();
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError>HandleAnyTypeOfException(Exception exception){
        ApiError apiError=ApiError.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).message(exception.getMessage()).build();
        return new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError>inputValidationException(MethodArgumentNotValidException exception){
       List<String> errors=exception.getBindingResult().getAllErrors().stream().map(error->error.getDefaultMessage()).collect(Collectors.toList());
       ApiError apiError=ApiError.builder().status(HttpStatus.BAD_REQUEST).message(errors.toString()).error(null).build();
       return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);

    }




}
