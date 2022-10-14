package dev.azlabs.modernclient.core;

import javafx.util.Callback;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class RootInjector {

    private static final Map<Class<?>, Callback<Class<?>, Object>> methods = new HashMap<>();

    /**
     * Determine whether a stored method is available
     * If one is, return the custom controller
     * If one is not, return the default controller
     * @param abstractClass the class of controller to be created
     * @return the controller created
     */
    public static Object make(Class<?> abstractClass) {
        if(methods.containsKey(abstractClass)) {
            return loadController(abstractClass);
        } else {
            return newController(abstractClass);
        }
    }

    private static Object loadController(Class<?> controller){
        try {
            return methods.get(controller).call(controller);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
    private static Object newController(Class<?> controller){
        try {
            return controller.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Register a controller dependency into the injector
     */
    public static void provide(Class<?> key, Callback<Class<?>, Object> implementation) {
        methods.put(key, implementation);
    }
}
