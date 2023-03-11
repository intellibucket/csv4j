package az.rock.csv4j.serializer;

import az.rock.csv4j.EnvironmentInitializer;
import az.rock.csv4j.inspector.POJOInspector;

import java.util.List;

public abstract class AbstractSerializer<T> implements Serializer<T> , EnvironmentInitializer {
    private final List<T> data;
    private final POJOInspector<T> pojoInspector;

    public AbstractSerializer(List<T> data){
        this.pojoInspector = null;
        this.data = data;
    }

    @Override
    public void write() {
        this.preExecution();
        this.execute();
    }

    public abstract void execute();

    public POJOInspector<T> getPojoInspector() {
        return pojoInspector;
    }

    public List<T> getData() {
        return data;
    }
}
