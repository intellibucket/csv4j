package az.rock.csv4j.mapper;

import az.rock.csv4j.annotation.CSVElement;

import java.lang.reflect.Field;
import java.util.Objects;

public class POJOFieldReference {

    private CSVElement csvElement;

    private final Field field;

    public POJOFieldReference(Field field){
        this.field = field;
    }

    public Field getField() {
        return field;
    }

    private void init(){
        this.csvElement = this.field.getDeclaredAnnotation(CSVElement.class);
        if (!Objects.isNull(this.csvElement)){

        }
    }


    public boolean match(String headerName){
        this.init();
        if (Objects.isNull(this.csvElement)) return false;
        else {
            if (this.csvElement.name().trim().equals("")){
                return this.field.getName().equals(headerName);
            }else {
                return this.csvElement.name().equals(headerName);
            }
        }
    };
}
