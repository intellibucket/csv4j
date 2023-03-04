package az.rock.csv4j;

import az.rock.csv4j.annotation.CSVModel;
import az.rock.csv4j.mapper.LineReader;
import az.rock.csv4j.strategy.CSVRuntimeStrategy;
import az.rock.csv4j.strategy.CSVStrategyPrototype;
import org.reflections.Reflections;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

@SuppressWarnings("all")
public class CSVManager<T> {
    private final List<T> list = new ArrayList<>();
    private final CSVRuntimeStrategy runtimeStrategy;
    private final String resourcePath;
    private final Class<?> type;
    private String header;

    public CSVManager(Class<?> type,
                      String resourcePath,
                      CSVRuntimeStrategy runtimeStrategy) {
        this.resourcePath = resourcePath;
        this.type = type;
        this.runtimeStrategy  = runtimeStrategy;
    }

    public CSVManager(Class<?> type,String resourcePath) {
        this.resourcePath = resourcePath;
        this.type = type;
        this.runtimeStrategy = CSVStrategyPrototype.defaultStrategy();
    }



    public List<T> read() throws URISyntaxException, FileNotFoundException {
        this.list.clear();
        this.controlModelForRead(this.type);
        var csvFile = this.getCSVFromResource(this.resourcePath);
        Scanner scanner = new Scanner(csvFile);
        this.header = scanner.nextLine();
        while (scanner.hasNext()){
            this.list.add((T) this.map(scanner.nextLine()));
        }
        return this.list;
    }


    private void controlModelForRead(Class<?> type){
        Reflections reflections = new Reflections(type);
        var annotations = reflections.getTypesAnnotatedWith(CSVModel.class);
        if (annotations.isEmpty()) throw new RuntimeException("Not defined");
    }

    private Object map(String line){
        var lineReader = new LineReader<T>(this.header,(T) this.newInstance());
        return lineReader.mapLine(line);
    }

    private Object newInstance(){
        try {
            Constructor<?> constructor = type.getConstructor();
            return constructor.newInstance();
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private File getCSVFromResource(String resourcePath){
        URL resource = CSVManager.class.getClassLoader().getResource("MOCK_DATA.csv");
        try {
            Objects.requireNonNull(resource,"File not found Exception");
            return Paths.get(resource.toURI()).toFile();
        } catch (URISyntaxException e) {
            throw new RuntimeException("File not found Exception");
        }
    }

}
