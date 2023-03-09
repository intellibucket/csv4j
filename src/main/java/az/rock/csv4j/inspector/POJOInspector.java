package az.rock.csv4j.inspector;

import az.rock.csv4j.exception.ElementManyAnnotatedException;
import az.rock.csv4j.deserializer.loader.fileModel.CSVRawHeader;
import az.rock.csv4j.deserializer.loader.fileModel.CSVRawLine;

import java.util.Map;

public class POJOInspector<T> {
    private final Class<?> csvModelType;
    private ModelProperty<T> modelProperty;

    public static <T> POJOInspector<T> of(Class<T> tClass) throws ElementManyAnnotatedException {
        POJOInspector<T> pojoInspector = new POJOInspector<T>(tClass);
        pojoInspector.load();
        return pojoInspector;
    }

    private POJOInspector(Class<?> tClass){
        this.csvModelType = tClass;
    }


    @SuppressWarnings("unchecked")
    private void load() throws ElementManyAnnotatedException {
        this.modelProperty = (ModelProperty<T>) ModelProperty.of(this.csvModelType);
    }

    public T setState(T rawObject, CSVRawHeader csvRawHeader,CSVRawLine rawLine){
        HeaderValueTaker headerValueTaker =  new HeaderValueTaker(csvRawHeader);
        Map<String,String> dataCursor = headerValueTaker.nextLine(rawLine);
        Map<String,FieldProperty<T>> fieldIdentityProperty = this.modelProperty.getFieldIdentityProperty();
        var fieldKeySet = fieldIdentityProperty.keySet();
        for (String fieldIdentify:fieldKeySet){
            String fieldCSVColumnValue = dataCursor.get(fieldIdentify);
            FieldProperty<T> fieldProperty = fieldIdentityProperty.get(fieldIdentify);
            fieldProperty.setValue(rawObject,fieldCSVColumnValue);
        }
        return rawObject;
    }

}
