package az.rock.csv4j.inspector.type;

import java.lang.reflect.Field;
import java.util.UUID;

public class GUIDTypeReference<T> extends TypeReference<T>{
    @Override
    public void setValue(T object, Field field, String value) throws IllegalAccessException {
        UUID uuid = UUID.fromString(value);
        field.set(object,uuid);
    }
}
