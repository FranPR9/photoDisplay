package com.example.franciscopaniagua.photodisplays.lib;

/**
 * Created by franpr9.
 */
public class GreenRobotEventBus implements EventBus {
    org.greenrobot.eventbus.EventBus eventBus;


    public GreenRobotEventBus(){
        eventBus = org.greenrobot.eventbus.EventBus.getDefault();
    }

    public void register(Object subscriber){
        eventBus.register(subscriber);
    }

    public void unregister(Object subscriber){
        eventBus.unregister(subscriber);
    }

    public void post(Object event){
        eventBus.post(event);
    }

    @Override
    public boolean isRegister(Object event) {
        return eventBus.isRegistered(event);
    }
}
