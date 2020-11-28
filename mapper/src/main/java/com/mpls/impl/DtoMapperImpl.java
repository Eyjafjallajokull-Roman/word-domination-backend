package com.mpls.impl;

import com.mpls.DtoMapper;
import com.mpls.annotations.EnableMapper;
import com.mpls.annotations.MapperIgnore;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

@EnableMapper(dto = "com.mpls.web", model = "com.mpls.web")
public class DtoMapperImpl implements DtoMapper {
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(DtoMapperImpl.class);

    {
        init();
    }

    public void init() {
    }

    public Object parseFromDTOtoObject(Object dtoObject, Class... parsingClasses) {
        if (dtoObject == null)
            return null;
        try {
            final Object object = parsingClasses[0].newInstance();
            Arrays.stream(parsingClasses[0].getMethods())
                    .filter(method1 -> method1.getName().contains("set"))
                    .filter(method1 -> !haveAnnotation(MapperIgnore.class, method1.getName(), object))
                    .forEach(method1 -> {
                        try {
                            if (!method1.getParameterTypes()[0].equals(List.class) && (method1.getParameterTypes()[0].getSuperclass().equals(Enum.class)
                                    || method1.getParameterTypes()[0].equals(Enum.class)
                                    || method1.getParameterTypes()[0].equals(String.class)
                                    || method1.getParameterTypes()[0].equals(Character.class)
                                    || method1.getParameterTypes()[0].getSuperclass().equals(Number.class)
                                    || method1.getParameterTypes()[0].equals(LocalDate.class)
                                    || method1.getParameterTypes()[0].equals(Timestamp.class)
                                    || method1.getParameterTypes()[0].equals(Time.class)
                                    || method1.getParameterTypes()[0].equals(LocalDateTime.class)
                                    || method1.getParameterTypes()[0].equals(Boolean.class))) {
                                Arrays.stream(dtoObject.getClass().getMethods())
                                        .filter(method -> method.getName().contains("get"))
                                        .filter(method -> method.getName().replace("get", "").equals(method1.getName().replace("set", "")))
                                        .forEach(method -> {

                                            try {
                                                if (method.invoke(dtoObject) != null)
                                                    method1.invoke(object, method.invoke(dtoObject));
                                            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                                                logger.debug(method.getName());
                                                logger.debug(method1.getName());
                                                logger.debug(e);
                                            }
                                        });
                            } else {
                                Arrays.stream(dtoObject.getClass().getMethods())
                                        .filter(method -> method.getName().contains("get"))
                                        .filter(method -> method.getName().replace("get", "").equals(method1.getName().replace("set", "")))
                                        .forEach(method -> {
                                            try {
                                                if (method != null) {
                                                    if (method1.getParameterTypes()[0].equals(List.class)) {
                                                        List a = (List) method.invoke(dtoObject);
                                                        if (a == null) {
                                                            a = new ArrayList();
                                                            method1.invoke(object, a);//todo
                                                        } else {
                                                            if (method.getName() != null)
                                                                method1.invoke(object, a.stream().map(o -> (parseFromDTOtoObject(o, (Class) ((ParameterizedType) method1.getParameters()[0].getParameterizedType()).getActualTypeArguments()[0]))).collect(toList()));//todo

                                                        }
                                                    } else {
                                                        if (method.invoke(dtoObject) != null)
                                                            method1.invoke(object, parseFromDTOtoObject(method.invoke(dtoObject), method1.getParameterTypes()[0]));//todo
                                                    }
                                                }
                                            } catch (IllegalAccessException | InvocationTargetException e) {
                                                e.printStackTrace();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                                try {
                                                    logger.debug("found an exception");
                                                    logger.debug(method1.getName());
                                                } catch (Exception ee) {
                                                    logger.debug(e);
                                                }
                                            }
                                        });
                            }
                        } catch (NullPointerException e) {
                            logger.debug(method1.getParameterTypes()[0].getName());
                            logger.debug(method1.getName());
                            logger.debug(parsingClasses[0].getName());
                            e.printStackTrace();
                        }
                    });
            return object;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }


        return null;
    }


    private Field findFiled(String name, Object o) throws NoSuchFieldException {
        StringBuilder builder = new StringBuilder();
        if (name.startsWith("get")) {
            name = name.replace("get", "");
            builder.append(String.valueOf(name.charAt(0)).toLowerCase());
            for (int i = 1; i < name.length(); i++) {
                builder.append(name.charAt(i));
            }
        } else if (name.startsWith("set")) {
            name = name.replace("set", "");
            builder.append(String.valueOf(name.charAt(0)).toLowerCase());
            for (int i = 1; i < name.length(); i++) {
                builder.append(name.charAt(i));
            }
        } else {
            builder.append(name);
        }
        Field f = findFieldSupperClass(o.getClass(), builder); //NoSuchFieldException
        f.setAccessible(true);
        return f;
    }

    private Field findFieldSupperClass(Class<?> type, StringBuilder builder) throws NoSuchFieldException {
        Class<?> i = type;
        while (i != null && i != Object.class) {
            try {
                return type.getDeclaredField(builder.toString());
            } catch (NoSuchFieldException e) {
            }
            i = i.getSuperclass();
        }
        throw new NoSuchFieldException("no field exception");
    }

    private Annotation[] findAnnotate(Field field) {
        return field.getDeclaredAnnotations();
    }

    private Boolean haveAnnotation(Class<Annotation> annotation, Field field) {
        Annotation[] annotations = findAnnotate(field);
        for (int i = 0; i < annotations.length; i++) {
            if (annotations[i].getClass().isAnnotation() && annotations[i].annotationType().equals(annotation)) {
                return true;
            }
        }
        return false;
    }

    private Boolean haveAnnotation(Class<?> annotation, String field, Object o) {
        Annotation[] annotations = null;
        try {
            annotations = findAnnotate(findFiled(field, o));
        } catch (NoSuchFieldException e) {
            logger.debug(e);
            return false;
        }
        for (int i = 0; i < annotations.length; i++) {
            logger.debug("[" + field + "]--------------[" + annotations[i].annotationType().equals(annotation) + "]---[" + annotation + "]-------[" + annotations[i].annotationType() + "]------");
            if (annotations[i].annotationType().equals(annotation)) {
                return true;
            }
        }
        logger.debug("[" + field + "]no dont nave annotations------------------------------");
        return false;
    }

    private void parser(Object dtoObject, Object parsedObject, Method[] methods) {
        if (dtoObject == null)
            return;
        Arrays.stream(parsedObject.getClass().getMethods()).filter(method -> method.getName().contains("set")).forEach(
                method -> {
                    if ((method.getParameterTypes()[0].getSuperclass().equals(Enum.class)
                            || method.getParameterTypes()[0].equals(Enum.class)
                            || method.getParameterTypes()[0].equals(String.class)
                            || method.getParameterTypes()[0].equals(Character.class)
                            || method.getParameterTypes()[0].getSuperclass().equals(Number.class)
                            || method.getParameterTypes()[0].equals(LocalDate.class)
                            || method.getParameterTypes()[0].equals(Timestamp.class)
                            || method.getParameterTypes()[0].equals(Time.class)
                            || method.getParameterTypes()[0].equals(LocalDateTime.class))) {


                        if (Arrays.stream(methods).filter(method1 -> method.getName().equals(method1.getName())).count() != 0) {

                            Method temp = Arrays.stream(methods).filter(method1 -> method1.getName().contains("get")).filter(method1 -> method.getName().replace("set", "").equals(method1.getName().replace("get", ""))).findFirst().get();

                            try {
                                method.invoke(parsedObject, temp.invoke(dtoObject));
                            } catch (IllegalAccessException | InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        );
    }
}
