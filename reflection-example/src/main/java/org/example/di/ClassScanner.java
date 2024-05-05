package org.example.di;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClassScanner {

    public static List<Class> getAllComponents(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
                                        .getResourceAsStream(packageName.replaceAll("[.]", "/"));

        List<String> directoryContent = new BufferedReader(new InputStreamReader(stream))
                .lines()
                .collect(Collectors.toList());


        List<Class> classesInCurrentDirectory =
                directoryContent.stream()
                                .filter(line -> line.endsWith(".class"))
                                .map(className -> getClass(className, packageName))
                                .filter(clazz -> clazz.isAnnotationPresent(ExampleComponent.class))
                                .collect(Collectors.toList());

        List<Class> classesInSubDirectories =
                directoryContent.stream()
                                .filter(line -> !line.contains("."))
                                .map(line -> packageName + '.' + line)
                                .map(ClassScanner::getAllComponents)
                                .flatMap(List::stream)
                                .collect(Collectors.toList());

        return Stream.of(classesInCurrentDirectory, classesInSubDirectories)
                     .flatMap(List::stream)
                     .sorted(Comparator.comparing(ClassScanner::getPriority))
                     .collect(Collectors.toList());
    }

    private static int getPriority(Class clazz) {
        return ((ExampleComponent) clazz.getAnnotation(ExampleComponent.class)).priority();
    }

    private static Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "." + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            System.err.println(STR. "Class '\{ packageName }.\{ className } was not found." );
        }
        return null;
    }
}
