package az.rock.csv4j.strategy;

public class CSVStrategyPrototype {
    public static CSVRuntimeStrategy defaultStrategy(){
        return new CSVRuntimeStrategy();
    }
}
