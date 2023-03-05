package az.rock.csv4j.exception;

public class CSVHeaderNotFoundException extends Exception{
    public CSVHeaderNotFoundException(){
        super("CSV header is not found");
    }
}
