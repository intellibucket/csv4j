package az.rock.csv4j.serializer;

import az.rock.csv4j.exception.ElementManyAnnotatedException;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class ParallelCSVSerializer<T> extends CSVSerializer<T>{
    private final ExecutorService executorService;

    public ParallelCSVSerializer(List<T> data, File file) throws ElementManyAnnotatedException {
        super(data, file);
        this.executorService = GExecutorService.defaultService();
    }

    public ParallelCSVSerializer(List<T> data, String path , ExecutorService executorService) {
        super(data, path);
        this.executorService = GExecutorService.of(executorService);
    }


    @Override
    public void preExecution() {

    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {

        }
    };

    @Override
    public void execute() {
        this.executorService.execute(this.runnable);
    }




}
