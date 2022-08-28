package com.ar.villaf.backendCourseManagmentSystem.exception;

import com.ar.villaf.backendCourseManagmentSystem.entity.ErrorBody;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserIdNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFound (UserIdNotFoundException ex){
        return new ResponseEntity<>(new ErrorBody(HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value(),Arrays.asList(ex.getMessage())), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(RoleNameNotFoundException.class)
    public ResponseEntity<Object> handleRoleNotFound (RoleNameNotFoundException ex){
        return new ResponseEntity<>(new ErrorBody(HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value(), Arrays.asList(ex.getMessage())), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(CourseIdNotFoundException.class)
    public ResponseEntity<Object> handleCourseNotFound (CourseIdNotFoundException ex){
        return new ResponseEntity<>(new ErrorBody(HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value(), Arrays.asList(ex.getMessage())), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<Object> handleInvalidFormat (InvalidFormatException ex){
        return new ResponseEntity<>(new ErrorBody(HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value(), Arrays.asList(ex.getMessage())), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(VideoNameDuplicatedException.class)
    public ResponseEntity<Object> handleVideoAlreadyExist (VideoNameDuplicatedException ex){
        return new ResponseEntity<>(new ErrorBody(HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value(), Arrays.asList(ex.getMessage())), HttpStatus.BAD_REQUEST);
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());
        return new ResponseEntity<>(new ErrorBody(HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value(), errors), HttpStatus.NOT_FOUND);
    }
}
