package com.ar.villaf.backendCourseManagmentSystem.exception;

public class InvalidFormatException  extends RuntimeException{
    public InvalidFormatException (String format) {
        super("Video with format " + format + " should be MP4");
    }
}
