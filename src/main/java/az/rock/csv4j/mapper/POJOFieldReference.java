package az.rock.csv4j.mapper;

import az.rock.csv4j.annotation.CSVColumn;

import java.lang.reflect.Field;
import java.util.Objects;

public class POJOFieldReference {

    private CSVColumn csvColumn;

    private final Field field;

    public POJOFieldReference(Field field){
        this.field = field;
    }

    public Field getField() {
        return field;
    }

    private void init(){
        this.csvColumn = this.field.getDeclaredAnnotation(CSVColumn.class);
        if (!Objects.isNull(this.csvColumn)){

        }
    }


    public boolean match(String headerName){
        this.init();
        if (Objects.isNull(this.csvColumn)) return false;
        else {
            if (this.csvColumn.name().trim().equals("")){
                return this.field.getName().equals(headerName);
            }else {
                return this.csvColumn.name().equals(headerName);
            }
        }
    };
}
