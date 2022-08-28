package com.ar.villaf.backendCourseManagmentSystem.exception;

public class VideoNameDuplicatedException extends RuntimeException{
    public VideoNameDuplicatedException(String videoName) {
        super("Video with name: " + videoName + " already exist in this course");
    }
}
