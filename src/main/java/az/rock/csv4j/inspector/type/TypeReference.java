package az.rock.csv4j.inspector.type;

import java.lang.reflect.Field;

public abstract class TypeReference<T> {
    public abstract void setValue(T object, Field field, String value) throws IllegalAccessException;

    public void set(T object, Field field, String value){
        field.setAccessible(true);
        try {
            this.setValue(object, field, value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }finally {
            field.setAccessible(false);
        }

    }
}
