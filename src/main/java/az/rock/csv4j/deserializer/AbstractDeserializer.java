package az.rock.csv4j.deserializer;

import az.rock.csv4j.EnvironmentInitializer;
import az.rock.csv4j.exception.ElementManyAnnotatedException;
import az.rock.csv4j.inspector.POJOInspector;
import az.rock.csv4j.serializer.Serializer;

import java.util.List;

public abstract class AbstractDeserializer<T> implements Deserializer <T> , EnvironmentInitializer {
    private final Class<T> tClass;
    private final POJOInspector<T> pojoInspector;

    public AbstractDeserializer(Class<T> tClass){
        this.pojoInspector = null;
        this.tClass = tClass;
    }

    @Override
    public List<T> read() throws ElementManyAnnotatedException {
        this.preExecution();
        return this.execute();
    }

    public abstract List<T> execute();


    public Class<T> gettClass() {
        return tClass;
    }

    public POJOInspector<T> getPojoInspector() {
        return pojoInspector;
    }
}
