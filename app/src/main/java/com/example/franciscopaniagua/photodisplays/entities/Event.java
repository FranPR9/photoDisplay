package com.example.franciscopaniagua.photodisplays.entities;


import lombok.Getter;
import lombok.Setter;

public class Event {
    @Getter
    @Setter
    private int type;
    @Getter
    @Setter
    private String error;
    @Getter
    @Setter
    private boolean success;


}
