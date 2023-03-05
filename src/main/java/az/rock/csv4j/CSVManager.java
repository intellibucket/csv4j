package az.rock.csv4j;

import az.rock.csv4j.annotation.CSVModel;
import az.rock.csv4j.annotation.CSVReference;
import az.rock.csv4j.mapper.LineReader;
import az.rock.csv4j.strategy.CSVRuntimeStrategy;
import az.rock.csv4j.strategy.CSVStrategyPrototype;
import org.reflections.Reflections;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
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
    private final Class<?> mainPojoType;
    private String header;

    public CSVManager(Class<?> mainPojoType,
                      String resourcePath,
                      CSVRuntimeStrategy runtimeStrategy) {
        this.resourcePath = resourcePath;
        this.mainPojoType = mainPojoType;
        this.runtimeStrategy  = runtimeStrategy;
    }

    public CSVManager(Class<?> mainPojoType, String resourcePath) {
        this.resourcePath = resourcePath;
        this.mainPojoType = mainPojoType;
        this.runtimeStrategy = CSVStrategyPrototype.defaultStrategy();
    }



    public List<T> read() throws URISyntaxException, FileNotFoundException {
        this.list.clear();
        this.controlModelForRead(this.mainPojoType);
        var csvFile = this.getCSVFromResource(this.resourcePath);
        Scanner scanner = new Scanner(csvFile);
        this.header = scanner.nextLine();
        while (scanner.hasNext()){
            this.list.add(this.map(scanner.nextLine()));
        }
        return this.list;
    }


    private void controlModelForRead(Class<?> type){
        Reflections reflections = new Reflections(type);
        var annotations = reflections.getTypesAnnotatedWith(CSVModel.class);
        if (annotations.isEmpty()) throw new RuntimeException("Not defined");
    }

    private T map(String line){
        Object object = this.newInstance(this.mainPojoType);
        var lineReader = new LineReader<T>(this.header, (T) this.loadAnyReferences(object));
        return lineReader.mapLine(line);
    }

    private Object newInstance(Class<?> type){
        try {
            Constructor<?> constructor =  type.getConstructor();
            return constructor.newInstance();
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


    private Object loadAnyReferences(Object object){
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field:fields){
            this.iterateFileds(object,field);
        }

        return  object;
    }

    private void  iterateFileds(Object obj,Field field){
        if (field.isAnnotationPresent(CSVReference.class)){
            field.setAccessible(true);
            try {
                Object fieldObject = this.newInstance(field.getType());
                field.set(obj,fieldObject);
                this.loadAnyReferences(fieldObject);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }finally {
                field.setAccessible(false);
            }
        }else return;
    }



    private File getCSVFromResource(String resourcePath){
        URL resource = CSVManager.class.getClassLoader().getResource(resourcePath);
        try {
            Objects.requireNonNull(resource,"File not found Exception");
            return Paths.get(resource.toURI()).toFile();
        } catch (URISyntaxException e) {
            throw new RuntimeException("File not found Exception");
        }
    }

}
