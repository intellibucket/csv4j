package az.rock.csv4j.serializer;

import az.rock.csv4j.exception.ElementManyAnnotatedException;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class ParallelCSVSerializer<T> extends AbstractCSVSerializer<T> {

    public ParallelCSVSerializer(List<T> data, File file) throws ElementManyAnnotatedException {
        super(data, file);
    }

    public ParallelCSVSerializer(List<T> data, String path , ExecutorService executorService) {
        super(data, path);
    }

    @Override
    public void execute() {
        this.getData().parallelStream().forEach(this.rowWriter);
    }

}
