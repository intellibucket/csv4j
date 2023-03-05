package az.rock.csv4j.inspector.type;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.UUID;

public class DateTypeReference <T> extends TypeReference<T>{
    @Override
    public void setValue(T object, Field field, String value) throws IllegalAccessException {
        Date date = new Date(value);
        field.set(object,date);
    }
}
