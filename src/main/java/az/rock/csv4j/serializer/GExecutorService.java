package az.rock.csv4j.serializer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GExecutorService {
    private  ExecutorService executorService;

    private GExecutorService() {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        var numberOfThreads = availableProcessors * (1 + 50 / 5);
        this.executorService = Executors.newFixedThreadPool(numberOfThreads);
    }

    private GExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public static GExecutorService defaultService(){
        return new GExecutorService();
    }

    public static GExecutorService of(ExecutorService executorService){
        return new GExecutorService(executorService);
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }
}
