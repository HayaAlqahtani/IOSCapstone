package com.coded.gymSeeker.util;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException (String str){
        super(str);
    }
}
