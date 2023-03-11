package az.rock.csv4j.serializer;

import az.rock.csv4j.exception.ElementManyAnnotatedException;
import az.rock.csv4j.inspector.ModelProperty;
import az.rock.csv4j.util.EnvironmentInitializer;
import az.rock.csv4j.inspector.POJOInspector;

import java.util.List;

public abstract class AbstractSerializer<T> implements Serializer<T> , EnvironmentInitializer {
    private final List<T> data;

    private ModelProperty<T> modelProperty;

    public AbstractSerializer(List<T> data){
        this.data = data;
    }

    @Override
    public void write() {
        if (!data.isEmpty() && this.initModelProperty()){
            this.preExecution();
            this.execute();
        }
    }

    @SuppressWarnings(value = "unchecked")
    private boolean initModelProperty(){
        var elementType = this.getData().get(0).getClass();
        try {
            this.modelProperty = (ModelProperty<T>) ModelProperty.of(elementType);
            return true;
        } catch (ElementManyAnnotatedException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract void execute();


    public List<T> getData() {
        return data;
    }
}
