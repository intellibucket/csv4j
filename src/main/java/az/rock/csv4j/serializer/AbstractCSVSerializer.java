package az.rock.csv4j.serializer;

import az.rock.csv4j.exception.ElementManyAnnotatedException;

import java.io.File;
import java.util.List;
import java.util.function.Consumer;

public abstract class AbstractCSVSerializer<T> extends AbstractSerializer<T>{
    private final File file;

    public AbstractCSVSerializer(List<T> data, File file) throws ElementManyAnnotatedException {
        super(data);
        this.file = file;
    }

    public AbstractCSVSerializer(List<T> data, String path){
        super(data);
        this.file = new File(path);
    }

    @Override
    public void preExecution() {
        //prepare environments
    }

    protected Consumer<T> rowWriter = (rowElement)->{
        //row based operation

    };
}
