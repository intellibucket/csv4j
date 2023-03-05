package az.rock.csv4j.inspector;

import az.rock.csv4j.annotation.CSVColumn;
import az.rock.csv4j.exception.ElementManyAnnotatedException;

import java.lang.reflect.Field;

public class FieldProperty<T> {
    private final Field field;
    private CSVColumn csvColumnAnnotation;
    private Boolean available;

    public static <T> FieldProperty<T> of(Field field) throws ElementManyAnnotatedException {
        FieldProperty<T> fieldProperty = new FieldProperty<>(field);
        fieldProperty.init();
        return fieldProperty;
    }

    private FieldProperty(Field field){
        this.field = field;
    }

    private void init() throws ElementManyAnnotatedException {
        if (this.isCSVPresent()) {
            this.available = Boolean.TRUE;
            this.csvColumnAnnotation = this.field.getDeclaredAnnotation(CSVColumn.class);
        }
    }

    public Boolean isCSVPresent(){
        return this.field.isAnnotationPresent(CSVColumn.class);
    }

    public String getIdentityName(){
        if (this.isCSVPresent()){
            if (this.csvColumnAnnotation.name().trim().equals("")){
                return this.field.getName();
            }else return this.csvColumnAnnotation.name();
        }
        return "";
    }

    public void setValue(T object,String value){
        this.csvColumnAnnotation.type().typeReference().set(object,this.field,value);
    }

    private boolean isPrimitive(){
        return this.field.getType().isPrimitive();
    }

    private boolean isEnum(){
        return this.field.getType().isEnum();
    }

    private boolean isArray(){
        return this.field.getType().isArray();
    }

    private boolean isInterface(){
        return this.field.getType().isInterface();
    }

    private boolean isRecord(){
        return this.field.getType().isRecord();
    }

    private boolean isSealed(){
        return this.field.getType().isSealed();
    }

    private boolean isSynthetic(){
        return this.field.getType().isSynthetic();
    }

    public Boolean isAvailable() {
        return available;
    }
}
