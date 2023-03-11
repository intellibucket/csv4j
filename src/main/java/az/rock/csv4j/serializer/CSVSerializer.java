package az.rock.csv4j.serializer;

import az.rock.csv4j.exception.ElementManyAnnotatedException;

import java.io.File;
import java.util.List;

public class CSVSerializer <T> extends AbstractSerializer<T>{
    private final File file;

    public CSVSerializer(List<T> data, File file) throws ElementManyAnnotatedException {
        super(data);
        this.file = file;
    }

    public CSVSerializer(List<T> data, String path){
        super(data);
        this.file = new File(path);
    }


    @Override
    public void preExecution() {

    }

    @Override
    public void execute() {

    }
}
