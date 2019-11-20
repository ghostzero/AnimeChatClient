package me.ghostzero.chat.api.socket.events;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

public class EventManager {
    private Collection<EventListener> handlers = new ArrayList<>();

    public void registerEvents(EventListener aClass) {
        handlers.add(aClass);
    }

    public Event fireEvent(Event event) {
        for (EventListener registered : handlers) {
            for (Method method : registered.getClass().getMethods()) {
                EventHandler eventHandler = method.getAnnotation(EventHandler.class);
                if (eventHandler != null) {
                    Class<?>[] methodParams = method.getParameterTypes();

                    if (methodParams.length < 1) {
                        continue;
                    }

                    if (!event.getClass().getSimpleName().equals(methodParams[0].getSimpleName())) {
                        continue;
                    }

                    try {
                        method.invoke(registered, event);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                        System.err.println(exception.getMessage());
                    }
                }
            }
        }
        return event;
    }

    public Event fireEvent(Object json, Class<?> classOfT) throws JsonSyntaxException {
        return fireEvent((Event) new Gson().fromJson(json.toString(), classOfT));
    }
}
