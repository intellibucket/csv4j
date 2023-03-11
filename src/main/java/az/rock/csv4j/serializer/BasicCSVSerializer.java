package az.rock.csv4j.serializer;

import az.rock.csv4j.exception.ElementManyAnnotatedException;

import java.io.File;
import java.util.List;

public class BasicCSVSerializer<T> extends CSVSerializer<T>{

    public BasicCSVSerializer(List<T> data, File file) throws ElementManyAnnotatedException {
        super(data, file);
    }

    public BasicCSVSerializer(List<T> data, String path) {
        super(data, path);
    }

    @Override
    public void execute() {

    }

    @Override
    public void preExecution() {

    }
}
