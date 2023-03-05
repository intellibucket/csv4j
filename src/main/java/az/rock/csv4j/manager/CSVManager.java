package az.rock.csv4j.manager;

import az.rock.csv4j.exception.CSVHeaderNotFoundException;
import az.rock.csv4j.exception.ElementManyAnnotatedException;
import az.rock.csv4j.inspector.POJOInspector;
import az.rock.csv4j.loader.CSVLoader;
import az.rock.csv4j.loader.fileModel.CSVRawHeader;
import az.rock.csv4j.loader.fileModel.CSVRawLine;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class CSVManager<T> {

    private Class<T> tClass;
    private List<T> dataList = new ArrayList<>();
    private final CSVLoader csvLoader;
    private final POJOInspector pojoInspector;

    public CSVManager(Class<T> tClass,String csvResourcePath) throws CSVHeaderNotFoundException, ElementManyAnnotatedException {
        this.tClass = tClass;
        this.csvLoader = CSVLoader.of(csvResourcePath);
        this.pojoInspector = POJOInspector.of(tClass);
    }

    public CSVLoader getCsvLoader() {
        return csvLoader;
    }

    public List<T> load() throws ElementManyAnnotatedException {
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
