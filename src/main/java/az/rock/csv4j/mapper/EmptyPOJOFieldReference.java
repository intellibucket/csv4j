package az.rock.csv4j.mapper;

import java.lang.reflect.Field;

public class EmptyPOJOFieldReference extends POJOFieldReference{
    public EmptyPOJOFieldReference() {
        super(null);
    }

    @Override
    public boolean match(String headerName) {
        return false;
    }
}
