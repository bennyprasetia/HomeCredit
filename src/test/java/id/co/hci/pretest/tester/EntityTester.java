package id.co.hci.pretest.tester;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;


public abstract class EntityTester<T> {

    private static final ImmutableMap<Class<?>, Supplier<?>> DEFAULT_MAPPERS;

    static {
        final Builder<Class<?>, Supplier<?>> mapperBuilder = ImmutableMap.builder();

        /* Primitives */
        mapperBuilder.put(int.class, () -> 0);
        mapperBuilder.put(double.class, () -> 0.0d);
        mapperBuilder.put(float.class, () -> 0.0f);
        mapperBuilder.put(long.class, () -> 0L);
        mapperBuilder.put(boolean.class, () -> true);
        mapperBuilder.put(short.class, () -> (short) 0);
        mapperBuilder.put(byte.class, () -> (byte) 0);
        mapperBuilder.put(char.class, () -> (char) 0);

        mapperBuilder.put(Integer.class, () -> 0);
        mapperBuilder.put(Double.class, () -> 0.0d);
        mapperBuilder.put(Float.class, () -> 0.0f);
        mapperBuilder.put(Long.class, () -> 0L);
        mapperBuilder.put(Boolean.class, () -> true);
        mapperBuilder.put(Short.class, () -> (short) 0);
        mapperBuilder.put(Byte.class, () -> (byte) 0);
        mapperBuilder.put(Character.class, () -> (char) 0);

        mapperBuilder.put(BigDecimal.class, () -> BigDecimal.ONE);
        mapperBuilder.put(Date.class, Date::new);

        /* Collection types */
        mapperBuilder.put(Set.class, Collections::emptySet);
        mapperBuilder.put(SortedSet.class, Collections::emptySortedSet);
        mapperBuilder.put(List.class, Collections::emptyList);
        mapperBuilder.put(Map.class, Collections::emptyMap);
        mapperBuilder.put(SortedMap.class, Collections::emptySortedMap);

        DEFAULT_MAPPERS = mapperBuilder.build();
    }

    /**
     * The get fields to ignore and try to test.
     */
    private final Set<String> ignoredFields;

    /**
     * A custom mapper. Normally used when the test class has abstract objects.
     */
    private final ImmutableMap<Class<?>, Supplier<?>> mappers;

    /**
     * Creates an instance of {@link EntityTester} with the default ignore fields.
     *
     * @param customMapper any custom mappers for a given class type
     * @param ignoreFields the getters which should be ignored (e.g: "getId" or "isActive")
     */
    public EntityTester(Map<Class<?>, Supplier<?>> customMapper, Set<String> ignoreFields) {
        this.ignoredFields = new HashSet<>();

        if (ignoreFields != null) {
            this.ignoredFields.addAll(ignoreFields);
        }
        this.ignoredFields.add("getClass");

        if (customMapper == null) {
            this.mappers = DEFAULT_MAPPERS;
        } else {
            final Builder<Class<?>, Supplier<?>> builder = ImmutableMap.builder();
            builder.putAll(customMapper);
            builder.putAll(DEFAULT_MAPPERS);
            this.mappers = builder.build();
        }
    }

    /**
     * Returns an instance to test the get and set methods.
     *
     * @return an instance to test the get and set methods
     */
    protected abstract T getInstance();

    /**
     * Creates an object for the given {@link Class}.
     *
     * @param fieldName the name of the field
     * @param clazz the {@link Class} type to create
     *
     * @return a new instance for the given {@link Class}
     */
    private Object createObject(String fieldName, Class<?> clazz) {
        final Supplier<?> supplier = this.mappers.get(clazz);

        if (supplier != null) {
            return supplier.get();
        }

        if (clazz.isEnum()) {
            return clazz.getEnumConstants();
        }

        try {
            return clazz.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            throw new RuntimeException("Unable to create objects for field '" + fieldName + "'.", e);
        }
    }

    /**
     * Tests all the getters and setters. Verifies that when a set method is called, that the get method
     * returns the same thing. This will also use reflection to set the field if no setter exists (mainly
     * used for user immutable entities but Hibernate normally populates).
     *
     * @throws Exception if an unexpected error occurs
     */
    @Test
    public void testGettersAndSetters() throws Exception {
        /* Sort items for consistent test runs */
        final SortedMap<String, GetterSetterPair> getterSetterMapping = new TreeMap<>();

        final T instance = getInstance();

        for (final Method method : instance.getClass().getMethods()) {
            final String methodName = method.getName();

            if (this.ignoredFields.contains(methodName)) {
                continue;
            }

            String objectName;
            if (methodName.startsWith("get") && method.getParameters().length == 0) {
                /* Found the get method */
                objectName = methodName.substring("get".length());
                GetterSetterPair getterSetterPair = getterSetterMapping.get(objectName);
                if (getterSetterPair == null) {
                    getterSetterPair = new GetterSetterPair();
                    getterSetterMapping.put(objectName, getterSetterPair);
                }

                getterSetterPair.setGetter(method);
            } else if (methodName.startsWith("set") && method.getParameters().length == 1) {
                /* Found the set method */
                objectName = methodName.substring("set".length());

                GetterSetterPair getterSetterPair = getterSetterMapping.get(objectName);
                if (getterSetterPair == null) {
                    getterSetterPair = new GetterSetterPair();
                    getterSetterMapping.put(objectName, getterSetterPair);
                }

                getterSetterPair.setSetter(method);
            } else if (methodName.startsWith("is") && method.getParameters().length == 0) {
                /* Found the method is, which already is a get method */
                objectName = methodName.substring("is".length());

                GetterSetterPair getterSetterPair = getterSetterMapping.get(objectName);
                if (getterSetterPair == null) {
                    getterSetterPair = new GetterSetterPair();
                    getterSetterMapping.put(objectName, getterSetterPair);
                }
                getterSetterPair.setGetter(method);
            }
        }


        /*
         * Found all our mappings. Now call the getter and setter or set the field via reflection and call
         * the getter it does not have a setter.
         */
        for (final Map.Entry<String, GetterSetterPair> entry : getterSetterMapping.entrySet()) {
            final GetterSetterPair pair = entry.getValue();

            final String objectName = entry.getKey();
            final String fieldName = objectName.substring(0, 1).toLowerCase() + objectName.substring(1);

            if (pair.hasGetterAndSetter()) {
                /* Create an object */
                final Class<?> parameterType = pair.getSetter().getParameterTypes()[0];
                final Object newObject = createObject(fieldName, parameterType);

                pair.getSetter().invoke(instance, newObject);
                callGetter(fieldName, pair.getGetter(), instance, newObject);
            }
        }
    }

    /**
     * Calls a getter and verifies the result is what expected.
     *
     * @param fieldName the field name (used for error messages)
     * @param getter the get method {@link Method}
     * @param instance the test instance
     * @param expected the expected result
     */
    private void callGetter(String fieldName, Method getter, T instance, Object expected)
        throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        final Object getResult = getter.invoke(instance);

        if (getter.getReturnType().isPrimitive()) {
            /* Calling assertEquals() here due to autoboxing of primitive to object type */
            assertEquals(fieldName + " is different", expected, getResult);
        } else {
            /* This is a normal object. The object passed in should be the exactly same object we get back */
            assertSame(fieldName + " is different", expected, getResult);
        }
    }


}
