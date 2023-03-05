package az.rock.csv4j.inspector;

import az.rock.csv4j.exception.ElementManyAnnotatedException;
import az.rock.csv4j.loader.fileModel.CSVRawLine;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class POJOInspector<T> {
    private final Class<?> csvModelType;
    private T object;
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

    public T setState(T rawObject, CSVRawLine rawLine){

        return rawObject;
    }

}
