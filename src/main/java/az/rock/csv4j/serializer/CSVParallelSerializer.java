package az.rock.csv4j.serializer;

import az.rock.csv4j.exception.ElementManyAnnotatedException;

import java.io.File;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CSVParallelSerializer<T> extends CSVSerializer<T>{
    private final ExecutorService executorService;

    public CSVParallelSerializer(List<T> data, File file) throws ElementManyAnnotatedException {
        super(data, file);
        this.executorService = GExecutorService.defaultService();
    }

    public CSVParallelSerializer(List<T> data, String path , ExecutorService executorService) {
        super(data, path);
        this.executorService = GExecutorService.of(executorService);
    }

    @Override
    public void execute() {
    }

    @Override
    public void preExecution() {

    }
}
