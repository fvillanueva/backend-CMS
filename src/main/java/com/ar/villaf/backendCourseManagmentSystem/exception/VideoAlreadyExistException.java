package com.ar.villaf.backendCourseManagmentSystem.exception;

public class VideoAlreadyExistException extends RuntimeException{
    public VideoAlreadyExistException(String videoName) {
        super("Video with name: " + videoName + " already exist in this course");
    }
}
