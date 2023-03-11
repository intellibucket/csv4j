package az.rock.csv4j.serializer;

import az.rock.csv4j.exception.ElementManyAnnotatedException;
import az.rock.csv4j.inspector.POJOInspector;

import java.io.File;
import java.util.List;

public class CSVSerializer <T> extends AbstractSerializer<T>{


    public CSVSerializer(List<T> data, File file) throws ElementManyAnnotatedException {
        super(data);
    }

    public CSVSerializer(List<T> data, String path){
        super(data);
    }


    @Override
    public void preExecution() {

    }

    @Override
    public void execute() {

    }
}
