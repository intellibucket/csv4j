package az.rock.csv4j.serializer;

import az.rock.csv4j.exception.ElementManyAnnotatedException;

import java.io.File;
import java.util.List;

public class CSVBasicSerializer<T> extends CSVSerializer<T>{

    public CSVBasicSerializer(List<T> data, File file) throws ElementManyAnnotatedException {
        super(data, file);
    }

    public CSVBasicSerializer(List<T> data, String path) {
        super(data, path);
    }

    @Override
    public void execute() {

    }

    @Override
    public void preExecution() {

    }
}
