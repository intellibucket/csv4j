package az.rock.csv4j.deserializer;

import az.rock.csv4j.deserializer.loader.CSVLoader;
import az.rock.csv4j.deserializer.loader.DefaultCSVLoader;
import az.rock.csv4j.deserializer.loader.fileModel.CSVRawHeader;
import az.rock.csv4j.deserializer.loader.fileModel.CSVRawLine;
import az.rock.csv4j.exception.CSVHeaderNotFoundException;
import az.rock.csv4j.exception.ElementManyAnnotatedException;
import az.rock.csv4j.inspector.POJOInspector;
import az.rock.csv4j.instace.AbstractInstanceProvider;
import az.rock.csv4j.instace.InstanceProvider;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class CSVDeserializer<T> extends AbstractDeserializer<T>{

    private List<T> dataList = new ArrayList<>();
    private final AbstractInstanceProvider instanceProvider = new InstanceProvider();

    private final CSVLoader csvLoader;
    private final POJOInspector pojoInspector;

    private List<CSVRawLine> rawLines;
    private CSVRawHeader csvRawHeader;

    public CSVDeserializer(Class<T> tClass, String csvResourcePath) throws CSVHeaderNotFoundException, ElementManyAnnotatedException {
        super(tClass);
        this.csvLoader = DefaultCSVLoader.of(csvResourcePath);
        this.pojoInspector = POJOInspector.of(tClass);
    }

    public CSVDeserializer(Class<T> tClass, CSVLoader csvLoader) throws CSVHeaderNotFoundException, ElementManyAnnotatedException {
        super(tClass);
        this.csvLoader = csvLoader;
        this.pojoInspector = POJOInspector.of(tClass);
    }

    @Override
    public void preExecution() {
        this.dataList.clear();
        this.csvRawHeader = this.csvLoader.getCsvFile().getHeader();
        this.rawLines = this.csvLoader.getCsvFile().getPureCSV();
    }

    @Override
    public List<T> execute() {
        this.rawLines.forEach(rawLine->{
            var result = pojoInspector.setState((T) instanceProvider.newInstance(super.gettClass()),csvRawHeader,rawLine);
            this.dataList.add((T) result);
        });
        return this.dataList;
    }
}
