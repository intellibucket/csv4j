package az.rock.csv4j.inspector.type;

import java.lang.reflect.Field;

public class TextTypeReference<T> extends TypeReference<T>{
    @Override
    public void setValue(T object, Field field, String value) throws IllegalAccessException {
        field.set(object,value);
    }
}
