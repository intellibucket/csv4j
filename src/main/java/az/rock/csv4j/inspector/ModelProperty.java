package az.rock.csv4j.inspector;

import az.rock.csv4j.exception.ElementManyAnnotatedException;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ModelProperty<T> {
    private Boolean hasHeader = Boolean.TRUE;
    private Integer availableField;
    private final Class<T> tClass;

    private final Map<String,FieldProperty<T>> fieldIdentityProperty = new HashMap<>();

    private ModelProperty(Class<T> tClass){
        this.tClass = tClass;
    }

    public static <T> ModelProperty<T> of(Class<T> tClass) throws ElementManyAnnotatedException {
        ModelProperty<T> modelProperty = new ModelProperty<T>(tClass);
        modelProperty.initProperty();
        return modelProperty;
    }

    private void initProperty() throws ElementManyAnnotatedException {
        var fields = this.tClass.getDeclaredFields();
        for (Field field: fields){
            FieldProperty<T> fieldProperty = FieldProperty.of(field);
            this.fieldIdentityProperty.put(fieldProperty.getIdentityName(),fieldProperty);
        }
    }

    public FieldProperty<T> get(String fieldIdentity){
        return this.fieldIdentityProperty.get(fieldIdentity);
    }
}
