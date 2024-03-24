package org.example.di;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class DIContext {

    private final Map<String, Object> beans = new HashMap<>();

    public void init(String packageName) {
        ClassScanner.getAllComponents(packageName)
                    .forEach(clazz -> beans.put(toBeanName(clazz.getSimpleName()), createBean(clazz)));
    }

    public Collection<String> getAllBeanClassNames() {
        return beans.values()
                    .stream()
                    .map(Object::getClass)
                    .map(Class::getSimpleName)
                    .collect(Collectors.toList());
    }

    public <BEAN_TYPE> BEAN_TYPE getBean(String name, Class<BEAN_TYPE> type) {
        if (beans.containsKey(name)) {
            return (BEAN_TYPE) beans.get(name);
        }

        throw new RuntimeException(STR. "Bean with name '\{ name }' was not found." );
    }

    public <BEAN_TYPE> BEAN_TYPE getBean(Class<BEAN_TYPE> type) {
        return (BEAN_TYPE) beans.values()
                                .stream()
                                .filter(bean -> bean.getClass().equals(type))
                                .findAny()
                                .orElseThrow(
                                        () -> new RuntimeException(STR. "Bean with type '\{ type }' was not found." ));
    }

    private Object createBean(Class type) {
        Constructor constructor = type.getDeclaredConstructors()[0];

        Object[] constructorParameters = new Object[constructor.getParameterCount()];
        Class[] parameterTypes = constructor.getParameterTypes();

        for (int i = 0; i < constructor.getParameterCount(); i++) {
            constructorParameters[i] = getBean(parameterTypes[i]);
        }

        try {
            return constructor.newInstance(constructorParameters);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(STR. "Could not create an instance of class \{ type }" );
        }
    }

    private String toBeanName(String className) {
        return className.replaceFirst(className.substring(0, 1), className.substring(0, 1).toLowerCase());
    }
}
