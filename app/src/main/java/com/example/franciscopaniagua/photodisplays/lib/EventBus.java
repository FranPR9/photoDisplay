package com.example.franciscopaniagua.photodisplays.lib;

/**
 * Created by franpr9.
 */
public interface EventBus {
    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);
    boolean isRegister(Object event);

}
