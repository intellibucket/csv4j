package az.rock.csv4j.mapper;

import java.lang.reflect.Field;

public class POJOFieldReference {

    private final Field field;

    public POJOFieldReference(Field field){
        this.field = field;
    }

    public Field getField() {
        return field;
    }

    public boolean match(String headerName){
        return false;
    };
}
