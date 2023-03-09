package az.rock.csv4j.deserializer;

import az.rock.csv4j.exception.CSVHeaderNotFoundException;
import az.rock.csv4j.exception.ElementManyAnnotatedException;
import az.rock.csv4j.inspector.POJOInspector;
import az.rock.csv4j.deserializer.loader.CSVLoader;
import az.rock.csv4j.deserializer.loader.DefaultCSVLoader;
import az.rock.csv4j.deserializer.loader.fileModel.CSVRawHeader;
import az.rock.csv4j.deserializer.loader.fileModel.CSVRawLine;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class CSVDeserializer<T> implements Deserializer<T>{

    private Class<T> tClass;
    private List<T> dataList = new ArrayList<>();
    private final CSVLoader csvLoader;
    private final POJOInspector pojoInspector;

    public CSVDeserializer(Class<T> tClass, String csvResourcePath) throws CSVHeaderNotFoundException, ElementManyAnnotatedException {
        this.tClass = tClass;
        this.csvLoader = DefaultCSVLoader.of(csvResourcePath);
        this.pojoInspector = POJOInspector.of(tClass);
    }

    public CSVDeserializer(Class<T> tClass, CSVLoader csvLoader) throws CSVHeaderNotFoundException, ElementManyAnnotatedException {
        this.tClass = tClass;
        this.csvLoader = csvLoader;
        this.pojoInspector = POJOInspector.of(tClass);
    }


    @Override
    public List<T> read() throws ElementManyAnnotatedException {
        this.dataList.clear();
        CSVRawHeader csvRawHeader = this.csvLoader.getCsvFile().getHeader();
        List<CSVRawLine> rawLines = this.csvLoader.getCsvFile().getPureCSV();
        rawLines.forEach(rawLine->{
            var result = pojoInspector.setState((T) this.newInstance(this.tClass),csvRawHeader,rawLine);
            this.dataList.add((T) result);
        });
        return this.dataList;
    }

    private  Object newInstance(Class<?> type){
        try {
            Constructor<?> constructor =  type.getConstructor();
            return constructor.newInstance();
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
